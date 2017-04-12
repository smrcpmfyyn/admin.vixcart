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
public class GetNoOfWebsitesSuccessResponse {
    private final String status;
    private final String accessToken;
    private int noOfWebsites;

    public GetNoOfWebsitesSuccessResponse(String status, String accessToken, int noOfWebsites) {
        this.status = status;
        this.accessToken = accessToken;
        this.noOfWebsites = noOfWebsites;
    }

    public GetNoOfWebsitesSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.noOfWebsites = 0;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getNoOfWebsites() {
        return noOfWebsites;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"noOfWebsites\":\""+noOfWebsites+"\"}";
        return response;
    }
}
