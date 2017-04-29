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
public class UpdateSpecificationResult implements Result {

    private String at;
    private String admintype;
    private String reqValidation;
    private String taspec;
    private String filtervisibility;
    private String onlinevisibility;
    private String offlinevisibility;

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

    public String getTaspec() {
        return taspec;
    }

    public void setTaspec(String taspec) {
        this.taspec = taspec;
    }

    public String getFiltervisibility() {
        return filtervisibility;
    }

    public void setFiltervisibility(String filtervisibility) {
        this.filtervisibility = filtervisibility;
    }

    public String getOnlinevisibility() {
        return onlinevisibility;
    }

    public void setOnlinevisibility(String onlinevisibility) {
        this.onlinevisibility = onlinevisibility;
    }

    public String getOfflinevisibility() {
        return offlinevisibility;
    }

    public void setOfflinevisibility(String offlinevisibility) {
        this.offlinevisibility = offlinevisibility;
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
            if (taspec.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "taspec#";
            }
            if (filtervisibility.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "filtervisibility#";
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
