/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.CheckUserType;

/**
 * @company techvay
 * @author rifaie
 */
public class CheckUserTypeValidation implements Validation {
    
    private final CheckUserType checkTyp;
    private String paramValue = "";
    private String paramName = "";

    public CheckUserTypeValidation(CheckUserType checkTyp) {
        this.checkTyp = checkTyp;
    }

    @Override
    public void validation() throws Exception {
        CheckUserTypeConstraints checkTypc = new CheckUserTypeConstraints(checkTyp);
        String valid = "";
        valid += checkTypc.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            String valid1 = checkTypc.validateUserType("management");
            valid += "#" + valid1;
            if(valid1.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                valid += "#"+checkTypc.validateTypeID();
            }
        }
        checkTypc.closeConnection();
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
        paramName += "checkTypeIDValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_CHECK_TYPE_ID;
        } else {
            paramValue += ErrMsg.ERR_CHECK_TYPE_ID;
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
