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
public class RPSuccessResponse {
    private final String status;
    private final String token;

    public RPSuccessResponse(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
    
    
    @Override
    public String toString() {
        return "{\"status\":\""+status + "\",\"token\":\""+token+"\"}";
    }
}
