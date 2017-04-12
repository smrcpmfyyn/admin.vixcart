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
public class UserType1 {
    private final String type;

    public UserType1(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return "{\"type\":\"" + type + "\"}";
    }
}
