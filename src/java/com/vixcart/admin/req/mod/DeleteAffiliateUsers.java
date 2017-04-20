/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.req.mod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @company techvay
 * @author rifaie
 */
public class DeleteAffiliateUsers {
    private final String at;
    private ArrayList<String> auids;
    private String admin_id;
    private String type;

    public DeleteAffiliateUsers(String at, String [] auid) {
        this.at = at;
        this.auids = new ArrayList<>(Arrays.asList(auid));
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

    public ArrayList<String> getAuids() {
        return auids;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getType() {
        return type;
    }
}
