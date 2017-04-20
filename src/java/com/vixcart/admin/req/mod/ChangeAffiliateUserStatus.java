/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.req.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class ChangeAffiliateUserStatus {
    private final String at;
    private final String user_id;
    private final String status;
    private String admin_id;
    private String type;

    public ChangeAffiliateUserStatus(String at, String user_id, String status) {
        this.at = at;
        this.user_id = user_id;
        this.status = status;
    }   

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAt() {
        return at;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getStatus() {
        return status;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getType() {
        return type;
    }
}
