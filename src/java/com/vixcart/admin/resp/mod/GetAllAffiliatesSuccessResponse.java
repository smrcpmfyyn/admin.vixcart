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
public class GetAllAffiliatesSuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<AllAffiliates> allAffiliates;

    public GetAllAffiliatesSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allAffiliates = new ArrayList<>();
    }

    public GetAllAffiliatesSuccessResponse(String status, String accessToken, ArrayList<AllAffiliates> allAffiliates) {
        this.status = status;
        this.accessToken = accessToken;
        this.allAffiliates = allAffiliates;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<AllAffiliates> getAllAffiliates() {
        return allAffiliates;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"affiliates\":[";
        response = allAffiliates.stream().map((AllAffiliates affiliate) -> affiliate.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
