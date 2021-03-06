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
public class EditUserResult implements Result {
    private String at;
    private String admintype;
    private String uid;
    private String name;
    private String designation;
    private String address1;
    private String address2;
    private String place;
    private String pin;
    private String typeid;
    private String editUserValidation;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEditUserValidation() {
        return editUserValidation;
    }

    public void setEditUserValidation(String editUserValidation) {
        this.editUserValidation = editUserValidation;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
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
        if (editUserValidation.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
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
            if(uid.startsWith(ErrMsg.ERR_MESSAGE)){
                error += "uid#";
            }
            if (name.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "name#";
            }
            if (address1.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "address1#";
            }
            if (address2.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "address2#";
            }
            if (place.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "place#";
            }
            if (pin.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "pin#";
            }
            if (typeid.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "type#";
            }           
        }
        return error.substring(0, error.length() - 1);
    }
    
    
}
