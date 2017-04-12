/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

import java.util.ArrayList;

/**
 * @company techvay
 * @author rifaie
 */
public class GetAffiliateUsersSuccessResponse {
    private final String status;
    private final String accessToken;
    private final ArrayList<AffiliateUserDetails> affiliateUsers;
    private final String queryStatus;

    public GetAffiliateUsersSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliateUsers = new ArrayList<>();
        this.queryStatus = "empty";
    }

    public GetAffiliateUsersSuccessResponse(String status, String accessToken, ArrayList<AffiliateUserDetails> affiliateUsers) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliateUsers = affiliateUsers;
        this.queryStatus = "available";
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<AffiliateUserDetails> getAffiliateUsers() {
        return affiliateUsers;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"qs\":\""+queryStatus +"\",\"auds\":[ ";
        response = affiliateUsers.stream().map((AffiliateUserDetails aud) -> aud+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}