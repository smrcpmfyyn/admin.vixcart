package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.GetCategories;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetCategoriesValidation implements Validation{

    private final GetCategories req;
    private String paramName = "";
    private String paramValue = "";

    public GetCategoriesValidation(GetCategories gsc) {
        this.req = gsc;
    }
    
    
    @Override
    public void validation() throws Exception {
        GetCategoriesConstraints reqC = new GetCategoriesConstraints(req);
        String valid = "";
        valid += reqC.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            valid += "#" + reqC.validateUserType("product management");
        }
//        System.out.println("valid = " + valid);
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
            paramValue += CorrectMsg.CORRECT_USER;
        } else {
            paramValue += ErrMsg.ERR_USER;
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
