package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.UpdateSubCategory;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateSubCategoryValidation implements Validation {

    private String paramValue = "";
    private String paramName = "";
    private UpdateSubCategory req;

    public UpdateSubCategoryValidation(UpdateSubCategory req) {
        this.req = req;
    }
    
    
    
    @Override
    public void validation() throws Exception {
        UpdateSubCategoryConstraints auc = new UpdateSubCategoryConstraints(req);
        String valid = "";
        valid += auc.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            String valid1 = auc.validateUserType("product management");
            valid += "#" + valid1;
            if (valid1.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                valid += "#" + auc.validateSubCategory();
                valid += "#" + auc.validateOnlineVisibilityStatus();
                valid += "#" + auc.validateOfflineVisibilityStatus();
            }
        }
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
            paramValue += CorrectMsg.CORRECT_UPDATE_SUB_CATEGORY;
        } else {
            paramValue += ErrMsg.ERR_UPDATE_SUB_CATEGORY;
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
