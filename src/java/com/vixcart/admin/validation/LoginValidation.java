/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.validation;

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.Login;

/**
 * @company techvay
 * @author rifaie
 */
public final class LoginValidation implements Validation {

    private final Login req;
    private String paramValue = "";
    private String paramName = "";

    public LoginValidation(Login log) {
        this.req = log;
    }

    @Override
    public void validation() throws Exception {
        LoginConstraints logc = new LoginConstraints(req);
        String valid = "";
        valid += logc.validateUserName();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            valid += "#" + logc.validatePassword();
        }
        logc.closeConnection();
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
        paramName += "logValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_LOG;
        } else {
            paramValue += ErrMsg.ERR_LOG;
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
