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
public class ChangeMemberStatus {
    private final String at;
    private final String member_id;
    private final String status;
    private String admin_id;
    private String type;

    public ChangeMemberStatus(String at, String member_id, String status) {
        this.at = at;
        this.member_id = member_id;
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

    public String getMember_id() {
        return member_id;
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
