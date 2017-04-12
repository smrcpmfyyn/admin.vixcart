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
public class AddUserType {
    private final String at;
    private String admin_id;
    private String utype;
    private final String type;
    private final String superType;

    public AddUserType(String at, String type, String superType) {
        this.at = at;
        this.type = type;
        this.superType = superType;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getAt() {
        return at;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getUtype() {
        return utype;
    }

    public String getType() {
        return type;
    }

    public String getSuperType() {
        return superType;
    }
    
    
}
