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
public class GetAllPremiumPaymentsSuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<AllPremiumPayments> allPremiumPayments;

    public GetAllPremiumPaymentsSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allPremiumPayments = new ArrayList<>();
    }

    public GetAllPremiumPaymentsSuccessResponse(String status, String accessToken, ArrayList<AllPremiumPayments> allPremiumPayments) {
        this.status = status;
        this.accessToken = accessToken;
        this.allPremiumPayments = allPremiumPayments;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<AllPremiumPayments> getAllPremiumPayments() {
        return allPremiumPayments;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"premiumPayment\":[";
        response = allPremiumPayments.stream().map((AllPremiumPayments premiumPayment) -> premiumPayment.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
