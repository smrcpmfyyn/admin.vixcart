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
public class GetAffiliateSuccessResponse {

    private final String status;
    private final String accessToken;
    private AffiliateDetails ad;

    public GetAffiliateSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public GetAffiliateSuccessResponse(String status, String accessToken, AffiliateDetails ud) {
        this.status = status;
        this.accessToken = accessToken;
        this.ad = ud;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AffiliateDetails getAd() {
        return ad;
    }

    @Override
    public String toString() {
        String response = "";
        if (ad.getCompany().equals("invalid")) {
            response = "{\"status\":\"" + status + "\"}";
        } else {
            response = "{\"status\":\"" + status + "\",\"ad\":" + ad.toString();
            response += "}";
        }
        return response;
    }
}
