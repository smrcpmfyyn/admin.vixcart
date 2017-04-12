/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.req.mod;

import com.vixcart.admin.hash.Hashing;

/**
 * @company techvay
 * @author rifaie
 */
public class Login {
    
    private final String uName;
    private String password;
    private String salt;
    private String admin_id;
    private String type;

    public Login(String uName, String password) {
        this.uName = uName;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getuName() {
        return uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }
    
    public void changePassword() throws Exception{
        password = Hashing.hashPassword(password.toCharArray(), salt.getBytes(), 24000, 256);
    }
}
