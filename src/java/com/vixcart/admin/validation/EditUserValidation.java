/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.EditUser;

/**
 * @company techvay
 * @author rifaie
 */
public class EditUserValidation implements Validation {
    
    private final EditUser eu;
    private String paramValue = "";
    private String paramName = "";

    public EditUserValidation(EditUser eu) {
        this.eu = eu;
    }

    @Override
    public void validation() throws Exception {
        EditUserConstraints euc = new EditUserConstraints(eu);
        String valid = "";
        valid += euc.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            String valid1 = euc.validateUserType("management");
            valid += "#" + valid1;
            if(valid1.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                valid += "#"+euc.validateUID();
                valid += "#"+euc.validateName();
                valid += "#"+euc.validateDesignation();
                valid += "#"+euc.validateAddress1();
                valid += "#"+euc.validateAddress2();
                valid += "#"+euc.validatePlace();
                valid += "#"+euc.validatePin();
                valid += "#"+euc.validateUtype();
            }
        }
        euc.closeConnection();
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
        paramName += "editUserValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_ADD_USER;
        } else {
            paramValue += ErrMsg.ERR_ADD_USER;
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
