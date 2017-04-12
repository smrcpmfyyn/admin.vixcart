/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.result;

import com.vixcart.admin.intfc.vres.Result;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.message.ValidationMsg;

/**
 * @company techvay
 * @author rifaie
 */
public class LogResult implements Result {

    private String uname;
    private String password;
    private String logValidation;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogValidation() {
        return logValidation;
    }

    public void setLogValidation(String logValidation) {
        this.logValidation = logValidation;
    }

    @Override
    public String getValidationResult() {
        String result;
        if (isRequestValid()) {
            result = ValidationMsg.VALID;
        } else {
            result = getAllErrors();
        }
        return result;
    }

    @Override
    public boolean isRequestValid() {
        boolean flag = false;
        if (logValidation.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public String getAllErrors() {
        String error = ErrMsg.ERR_ERR + "#";
        if (uname.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "uname#";
        } else if (password.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "password#";
        }
        return error.substring(0, error.length() - 1);
    }

}
