/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.mongo.mod;

import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;

/**
 * @company techvay
 * @author rifaie
 */
public class VerifyToken {
    private String admin_id;
    private String toe;
    private String status;

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getToe() {
        return toe;
    }

    public void setToe(String toe) {
        this.toe = toe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "VerifyToken{" + "admin_id=" + admin_id + ", toe=" + toe + ", status=" + status + '}';
    }

    public String getValidation() {
        String valid = ErrMsg.ERR_TOKEN;
        if(status.equals("verified")){
            valid = ErrMsg.ERR_TOKEN_USED;
        }else if(Long.parseLong(toe)<System.currentTimeMillis()){
            valid = ErrMsg.ERR_TOKEN_EXPIRED;
        }else if(status.equals("not changed")&&Long.parseLong(toe)>System.currentTimeMillis()){
            valid = CorrectMsg.CORRECT_TOKEN;
        }
        return valid;
    }
    
    
}
