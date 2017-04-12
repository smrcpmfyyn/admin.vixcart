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
public class GetUserSuccessResponse {
    private final String status;
    private final String accessToken;
    private UserDetails ud;

    public GetUserSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public GetUserSuccessResponse(String status, String accessToken, UserDetails ud) {
        this.status = status;
        this.accessToken = accessToken;
        this.ud = ud;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public UserDetails getUd() {
        return ud;
    }
    
    @Override
    public String toString() {
        String response = "";
        if (ud.getUname().equals("invalid")) {
            response = "{\"status\":\"" + status + "\"}";
        } else {
            response = "{\"status\":\"" + status + "\",\"types\":" + ud.toString();
            response += "}";
        }
        return response;
    }
}
