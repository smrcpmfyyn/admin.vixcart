package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.DeleteTaC;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class DeleteTaCValidation implements Validation{
    private final DeleteTaC req;
    private String paramValue = "";
    private String paramName = "";

    public DeleteTaCValidation(DeleteTaC req) {
        this.req = req;
    }

    @Override
    public void validation() throws Exception {
        DeleteTaCConstraints reqC = new DeleteTaCConstraints(req);
        String valid = "";
        valid += reqC.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            String valid1 = reqC.validateUserType("product management");
            valid += "#" + valid1;
            if(valid1.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                valid += "#"+reqC.validateTaC();
            }
        }
        reqC.closeConnection();
        int count = 0;
        for (String str : valid.split("#")) {
            paramName += str.split(" ")[1].toLowerCase() + "#";
            if (!str.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                count++;
                paramValue += str + "#";
            } else {
                paramValue += CorrectMsg.CORRECT_MESSAGE + "#";
            }
        }
        paramName += "reqValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_TAC;
        } else {
            paramValue += ErrMsg.ERR_TAC_NOT_EXISTS;
        }
    }
    
    @Override
    public String toString() {
        String[] paramsN = paramName.split("#");
        String[] paramV = paramValue.split("#");
        String json = "";
        for (int i = 0; i < paramsN.length; i++) {
            json += "\"" + paramsN[i] + "\"" + ":" + "\"" + paramV[i] + "\" ,";
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }
}
