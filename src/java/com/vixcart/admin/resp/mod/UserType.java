/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class UserType {
    private final String id;
    private final String type;
    private final String superType;

    public UserType(String id, String type, String superType) {
        this.id = id;
        this.type = type;
        this.superType = superType;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSuperType() {
        return superType;
    }
    
    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\", \"type\":\"" + type + "\", \"superType\":\"" + superType +"\"}";
    }
}
