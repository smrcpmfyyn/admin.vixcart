package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">

import java.util.ArrayList;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class SearchProductTypeSuccessResponse {

    private final String status;
    private final String accessToken;
    private final ArrayList<Query> queries;

    public SearchProductTypeSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.queries = new ArrayList<>();
    }

    public SearchProductTypeSuccessResponse(String status, String accessToken, ArrayList<Query> queries) {
        this.status = status;
        this.accessToken = accessToken;
        this.queries = queries;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"queries\":[ ";
        response = queries.stream().map((Query query) -> query.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
