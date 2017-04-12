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
public class UpdateUserType {
    private final String at;
    private String admin_id;
    private String utype;
    private final String typeid;
    private final String type;
    private final String superType;

    public UpdateUserType(String at, String typeid, String type, String superType) {
        this.at = at;
        this.typeid = typeid;
        this.type = type;
        this.superType = superType;
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

    public String getTypeid() {
        return typeid;
    }

    public String getType() {
        return type;
    }

    public String getSuperType() {
        return superType;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }
    
}
