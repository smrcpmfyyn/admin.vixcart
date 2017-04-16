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
	 private String on_status;
	 private String off_status;

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
      return  this.at;
    }

    public void setAdmintype(String admintype) {
        this.admintype = admintype;
    }

    public String getAdmintype() {
      return  this.admintype;
    }

    public void setReqvalidation(String reqValidation) {
        this.reqValidation = reqValidation;
    }

    public String getReqvalidation() {
      return  this.reqValidation;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
      return  this.brand;
    }

    public void setOn_status(String on_status) {
        this.on_status = on_status;
    }

    public String getOn_status() {
      return  this.on_status;
    }

    public void setOff_status(String off_status) {
        this.off_status = off_status;
    }

    public String getOff_status() {
      return  this.off_status;
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
            if (on_status.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "on_status#";
            }
            if (off_status.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "off_status#";
            }
        }
        return error.substring(0, error.length() - 1);
    }

}
