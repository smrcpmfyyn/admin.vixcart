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
public class UpdateAffiliateResult implements Result{
    private String at;
    private String admintype;
    private String ckcompany;
    private String company;
    private String status;
    private String mpp;
    private String cf;
    private String reqValidation;

    public String getMpp() {
        return mpp;
    }

    public void setMpp(String mpp) {
        this.mpp = mpp;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAdmintype() {
        return admintype;
    }

    public void setAdmintype(String admintype) {
        this.admintype = admintype;
    }

    public String getCkcompany() {
        return ckcompany;
    }

    public void setCkcompany(String ckcompany) {
        this.ckcompany = ckcompany;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReqValidation() {
        return reqValidation;
    }

    public void setReqValidation(String reqValidation) {
        this.reqValidation = reqValidation;
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
        } else {
            if (company.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "company#";
            }
            if (ckcompany.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "ckcompany#";
            }
            if (status.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "status#";
            }
            if (mpp.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "mpp#";
            }
            if (cf.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "cf#";
            }
        }
        return error.substring(0, error.length() - 1);
    }

}
