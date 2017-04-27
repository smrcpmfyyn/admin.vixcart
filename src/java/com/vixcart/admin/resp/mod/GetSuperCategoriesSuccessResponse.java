/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

import java.util.ArrayList;

public class GetSuperCategoriesSuccessResponse {

    private final String status;
    private final String accessToken;
    private ArrayList<SuperCategory> allCategories;

    public GetSuperCategoriesSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allCategories = new ArrayList<>();
    }
    public GetSuperCategoriesSuccessResponse(String status, String accessToken, ArrayList<SuperCategory> allCategories) {
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
        response = allCategories.stream().map((SuperCategory type) -> type.getSuperCategories()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
