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
public class GetNoOfMembers {
    private final String at;
    private String admin_id;
    private String type;

    public GetNoOfMembers(String at) {
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getType() {
        return type;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
