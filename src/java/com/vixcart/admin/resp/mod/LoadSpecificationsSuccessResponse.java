package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">

import java.util.ArrayList;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class LoadSpecificationsSuccessResponse {

    private final String status;
    private final String accessToken;
    private ArrayList<Specifications> res;

    public LoadSpecificationsSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.res = new ArrayList<>();
    }

    public LoadSpecificationsSuccessResponse(String status, String accessToken, ArrayList<Specifications> res) {
        this.status = status;
        this.accessToken = accessToken;
        this.res = res;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"brands\":[ ";
        response = res.stream().map((Specifications spec) -> spec.loadSpec()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
