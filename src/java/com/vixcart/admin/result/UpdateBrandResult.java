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
public class UpdateBrandResult implements Result {

    private String at;
    private String admintype;
    private String reqValidation;
    private String brand;
    private String onlinevisibility;
    private String offlinevisibility;

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    public void setAdmintype(String admintype) {
        this.admintype = admintype;
    }

    public String getAdmintype() {
        return this.admintype;
    }

    public void setReqvalidation(String reqValidation) {
        this.reqValidation = reqValidation;
    }

    public String getReqvalidation() {
        return this.reqValidation;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setOnlinevisibility(String onlinevisibility) {
        this.onlinevisibility = onlinevisibility;
    }

    public String getOnlinevisibility() {
        return this.onlinevisibility;
    }

    public void setOfflinevisibility(String offlinevisibility) {
        this.offlinevisibility = offlinevisibility;
    }

    public String getOfflinevisibility() {
        return this.offlinevisibility;
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
            if (brand.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "brand#";
            }
            if (onlinevisibility.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "onlinevisibility#";
            }
            if (offlinevisibility.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "onlinevisibility#";
            }
        }
        return error.substring(0, error.length() - 1);
    }

}
