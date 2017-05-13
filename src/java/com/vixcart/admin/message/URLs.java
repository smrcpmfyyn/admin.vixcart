/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.message;

/**
 * @company techvay
 * @author rifaie
 */
public class URLs {
    
    private static final String PASSWORD_GENERATION = "http://localhost:8080/admin.vixcart/resetPassword?token=";
    private static final String AFFILIATE_PASSWORD_GENERATION = "http://partner.vaydeal.com/resetPassword.html?token=";
    private static final String MEMBER_PASSWORD_GENERATION = "http://localhost:8080/member.vaydeal/resetPassword.html?token=";

    public static String getPASSWORD_GENERATION() {
        return PASSWORD_GENERATION;
    }

    public static String getAFFILIATE_PASSWORD_GENERATION() {
        return AFFILIATE_PASSWORD_GENERATION;
    }

    public static String getMEMBER_PASSWORD_GENERATION() {
        return MEMBER_PASSWORD_GENERATION;
    }
    
    
    
}
