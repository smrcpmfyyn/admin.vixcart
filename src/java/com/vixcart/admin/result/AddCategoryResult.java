package com.vixcart.admin.result;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.intfc.vres.Result;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.message.ValidationMsg;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class AddCategoryResult implements Result {

    private String at;
    private String admintype;
    private String categ;
    private String supercateg;
    private String reqValidation;

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

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getSupercateg() {
        return supercateg;
    }

    public void setSupercateg(String supercateg) {
        this.supercateg = supercateg;
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
            if (categ.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "category#";
            }
            if (supercateg.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "supercategory#";
            }
        }
        return error.substring(0, error.length() - 1);
    }

}