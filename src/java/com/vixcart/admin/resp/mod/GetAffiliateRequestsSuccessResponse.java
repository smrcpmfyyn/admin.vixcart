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
public class GetAffiliateRequestsSuccessResponse {
    private final String status;
    private final String accessToken;
    private final ArrayList<AffiliateRequests> affiliateRequests;
    private final String queryStatus;

    public GetAffiliateRequestsSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliateRequests = new ArrayList<>();
        this.queryStatus = "empty";
    }

    public GetAffiliateRequestsSuccessResponse(String status, String accessToken, ArrayList<AffiliateRequests> affiliateRequests) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliateRequests = affiliateRequests;
        this.queryStatus = "available";
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<AffiliateRequests> getAffiliateRequests() {
        return affiliateRequests;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"qs\":\""+queryStatus +"\",\"ars\":[ ";
        response = affiliateRequests.stream().map((AffiliateRequests aud) -> aud+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
