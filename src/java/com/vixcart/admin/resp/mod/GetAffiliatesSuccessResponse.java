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
public class GetAffiliatesSuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<AffiliateCompany> affiliates;

    public GetAffiliatesSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliates = new ArrayList<>();
    }

    public GetAffiliatesSuccessResponse(String status, String accessToken, ArrayList<AffiliateCompany> allAffiliates) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliates = allAffiliates;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<AffiliateCompany> getAffiliates() {
        return affiliates;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"affiliates\":[ ";
        response = affiliates.stream().map((AffiliateCompany ac) -> ac.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}