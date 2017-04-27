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
public class UpdateProductTypeResult implements Result {

    private String at;
    private String admintype;
    private String reqValidation;
    private String ptype;
    private String offlinevisibility;
    private String onlinevisibility;

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

    public String getReqValidation() {
        return reqValidation;
    }

    public void setReqValidation(String reqValidation) {
        this.reqValidation = reqValidation;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getOfflinevisibility() {
        return offlinevisibility;
    }

    public void setOfflinevisibility(String offlinevisibility) {
        this.offlinevisibility = offlinevisibility;
    }

    public String getOnlinevisibility() {
        return onlinevisibility;
    }

    public void setOnlinevisibility(String onlinevisibility) {
        this.onlinevisibility = onlinevisibility;
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
            if (ptype.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "ptype#";
            }
            if (onlinevisibility.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "onlinevisibility#";
            }
            if (offlinevisibility.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "offlinevisibility#";
            }
        }
        return error.substring(0, error.length() - 1);
    }

}
