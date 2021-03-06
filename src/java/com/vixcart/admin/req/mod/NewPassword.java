/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.req.mod;

import com.vixcart.admin.hash.Hashing;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class NewPassword {
    private final String token;
    private String adminId;
    private String newPassword;
    private String salt;

    public String getSalt() {
        return salt;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public NewPassword(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public String getNewPassword() {
        return newPassword;
    }
    
    /**
     * this will change the password
     *
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     */
    public void changePassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Random ran = new Random();
        int length = ran.nextInt(10) + 30;
        salt = Hashing.generateSalt(length);
        newPassword = Hashing.hashPassword(newPassword.toCharArray(), salt.getBytes(), 24000, 256);
    }
}
