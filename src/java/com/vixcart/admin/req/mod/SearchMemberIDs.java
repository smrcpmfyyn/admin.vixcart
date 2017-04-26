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
public class SearchMemberIDs {
    private final String at;
    private final String str;
    private String admin_id;
    private String type;

    public SearchMemberIDs(String at, String str) {
        this.at = at;
        this.str = str;
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

    public String getStr() {
        return str;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getType() {
        return type;
    }
}
