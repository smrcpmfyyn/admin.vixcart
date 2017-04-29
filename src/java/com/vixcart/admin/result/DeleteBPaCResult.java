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
public class DeleteBPaCResult implements Result {

    private String at;
    private String admintype;
    private String reqValidation;
    private String bpac;

    public DeleteBPaCResult(String at, String admintype, String reqValidation, String bpac) {
        this.at = at;
        this.admintype = admintype;
        this.reqValidation = reqValidation;
        this.bpac = bpac;
    }

    public String getAt() {
        return at;
    }

    public String getAdmintype() {
        return admintype;
    }

    public String getReqValidation() {
        return reqValidation;
    }

    public String getBpac() {
        return bpac;
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
        if (reqValidation.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public String getAllErrors() {
        String error = ErrMsg.ERR_ERR + "#";
        if (at.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "at#";
        } else if (admintype.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "admintype#";
        } else if (bpac.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "bpac#";
        }
        return error.substring(0, error.length() - 1);
    }
}
