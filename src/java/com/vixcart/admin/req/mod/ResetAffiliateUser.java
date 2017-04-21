/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.req.mod;

import com.vixcart.admin.hash.Hashing;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ResetAffiliateUser {
    private final String at;
    private String admin_id;
    private String type;
    private final String user_id;
    private String password;
    private String email;
    private String name;
    private String passwordToken;

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 44; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        password = sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }

    public ResetAffiliateUser(String at, String user_id) {
        this.at = at;
        this.user_id = user_id;
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

    public String getUser_id() {
        return user_id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getType() {
        return type;
    }
    
    public void setAutogeneratedValues() throws Exception{
        setPassword();
        genHash();
    }
    
    /**
     *
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws java.security.spec.InvalidKeySpecException
     */
    public void genHash() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException {
        passwordToken = Hashing.genVKey(email + password + System.currentTimeMillis());
    }
    
}