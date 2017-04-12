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
public class GetActivePremiumPaymentsSuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<PremiumPayments> activePremiumPayments;

    public GetActivePremiumPaymentsSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.activePremiumPayments = new ArrayList<>();
    }

    public GetActivePremiumPaymentsSuccessResponse(String status, String accessToken, ArrayList<PremiumPayments> activePremiumPayments) {
        this.status = status;
        this.accessToken = accessToken;
        this.activePremiumPayments = activePremiumPayments;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<PremiumPayments> getActivePremiumPayments() {
        return activePremiumPayments;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"activePremiumPayment\":[";
        response = activePremiumPayments.stream().map((PremiumPayments premiumPayment) -> premiumPayment.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
