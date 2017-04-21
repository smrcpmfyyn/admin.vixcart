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
public class GetAffiliateRequestSuccessResponse {

    private final String status;
    private final String accessToken;
    private AffiliateRequest ar;

    public GetAffiliateRequestSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public GetAffiliateRequestSuccessResponse(String status, String accessToken, AffiliateRequest ar) {
        this.status = status;
        this.accessToken = accessToken;
        this.ar = ar;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AffiliateRequest getAd() {
        return ar;
    }

    @Override
    public String toString() {
        String response = "";
        if (ar.getRid().equals("invalid")) {
            response = "{\"status\":\"" + status + "\"}";
        } else {
            response = "{\"status\":\"" + status + "\",\"ad\":" + ar.toString();
            response += "}";
        }
        return response;
    }
}

