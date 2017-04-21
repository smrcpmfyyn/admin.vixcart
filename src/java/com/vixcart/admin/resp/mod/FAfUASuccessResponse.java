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
public class FAfUASuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<AffiliateActivity> allFAfUA;

    public FAfUASuccessResponse(String status, String accessToken, ArrayList<AffiliateActivity> allFAfUA) {
        this.status = status;
        this.accessToken = accessToken;
        this.allFAfUA = allFAfUA;
    }

    public FAfUASuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allFAfUA = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<AffiliateActivity> getAllFAfUA() {
        return allFAfUA;
    }

    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"activities\":[ ";
        response = allFAfUA.stream().map((AffiliateActivity act) -> act.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
    
    
}


