package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.DeleteSubCategory;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class DeleteSubCategoryValidation implements Validation{


private final DeleteSubCategory req;
    private String paramValue = "";
    private String paramName = "";

    public DeleteSubCategoryValidation(DeleteSubCategory au) {
        this.req = au;
    }

    @Override
    public void validation() throws Exception {
        DeleteSubCategoryConstraints auc = new DeleteSubCategoryConstraints(req);
        String valid = "";
        valid += auc.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            String valid1 = auc.validateUserType("product management");
            valid += "#" + valid1;
            if(valid1.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                valid += "#"+auc.validateSubCategory();
            }
        }
        System.out.println("valid = " + valid);
        auc.closeConnection();
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
            paramValue += CorrectMsg.CORRECT_SUB_CATEGORY;
        } else {
            paramValue += ErrMsg.ERR_SUB_CATEGORY;
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
