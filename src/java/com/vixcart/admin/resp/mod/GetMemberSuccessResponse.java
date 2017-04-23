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
public class GetMemberSuccessResponse {

    private final String status;
    private final String accessToken;
    private final MemberAllDetails ad;

    public GetMemberSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        ad = new MemberAllDetails();
    }

    public GetMemberSuccessResponse(String status, String accessToken, MemberAllDetails ud) {
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

    public MemberAllDetails getAd() {
        return ad;
    }

    @Override
    public String toString() {
        String response = "";
        if (ad.getMid().equals("invalid")) {
            response = "{\"status\":\"" + status + "\"}";
        } else {
            response = "{\"status\":\"" + status + "\",\"ad\":" + ad.toString();
            response += "}";
        }
        return response;
    }
}

