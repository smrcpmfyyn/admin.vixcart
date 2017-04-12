/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.SuperUserTypes;

/**
 * @company techvay
 * @author rifaie
 */
public class SuperUserTypesValidation implements Validation {

    private final SuperUserTypes type;
    private String paramValue = "";
    private String paramName = "";

    public SuperUserTypesValidation(SuperUserTypes type) {
        this.type = type;
    }
    
    
    
    @Override
    public void validation() throws Exception {
        SuperUserTypesConstraints typec = new SuperUserTypesConstraints(type);
        String valid = "";
        valid += typec.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            valid += "#" + typec.validateUserType("management");
        }
        typec.closeConnection();
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
        paramName += "sTypesValidation";
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
