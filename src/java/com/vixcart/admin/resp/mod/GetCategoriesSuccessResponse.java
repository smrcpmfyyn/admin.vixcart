package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">

import java.util.ArrayList;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetCategoriesSuccessResponse {

    private final String status;
    private final String accessToken;
    private ArrayList<Category> allCategories;

    public GetCategoriesSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allCategories = new ArrayList<>();
    }
    public GetCategoriesSuccessResponse(String status, String accessToken, ArrayList<Category> allCategories) {
        this.status = status;
        this.accessToken = accessToken;
        this.allCategories = allCategories;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"categories\":[ ";
        response = allCategories.stream().map((Category type) -> type.getCategories()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
