/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.AddUserType;

/**
 * @company techvay
 * @author rifaie
 */
public class AddUserTypeValidation implements Validation {
    private final AddUserType addTyp;
    private String paramValue = "";
    private String paramName = "";

    public AddUserTypeValidation(AddUserType addTyp) {
        this.addTyp = addTyp;
    }

    @Override
    public void validation() throws Exception {
        AddUserTypeConstraints addTypc = new AddUserTypeConstraints(addTyp);
        String valid = "";
        valid += addTypc.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            String valid1 = addTypc.validateUserType("management");
            valid += "#" + valid1;
            if(valid1.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                valid += "#"+addTypc.validateType();
                valid += "#"+addTypc.validateSuperType();
            }
        }
        addTypc.closeConnection();
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
        paramName += "addTypeValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_ADD_TYPE;
        } else {
            paramValue += ErrMsg.ERR_ADD_TYPE;
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
