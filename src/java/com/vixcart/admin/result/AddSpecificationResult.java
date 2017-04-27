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
public class AddSpecificationResult implements Result {
	 private String at;
	 private String admintype;
	 private String reqValidation;
	 private String pType;
	 private String specification;

    public String getAt() {
        return at;
    }

    public String getReqValidation() {
        return reqValidation;
    }

    public String getPType() {
        return pType;
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

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSpecification() {
      return  this.specification;
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
            if (pType.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "ptype#";
            }
            if (specification.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "specification#";
            }
        }
        return error.substring(0, error.length() - 1);
    }

}
