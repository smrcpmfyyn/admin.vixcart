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
public class DeleteAffiliate {
    private final String at;
    private final String affiliate;
    private String admin_id;
    private String type;

    public DeleteAffiliate(String at, String affiliate) {
        this.at = at;
        this.affiliate = affiliate;
    }

    public String getAt() {
        return at;
    }

    public String getAffiliate() {
        return affiliate;
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
