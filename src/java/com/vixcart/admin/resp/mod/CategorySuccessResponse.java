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
public class CategorySuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<Category> allCategories;

    public CategorySuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public CategorySuccessResponse(String status, String accessToken, ArrayList<Category> allTypes) {
        this.status = status;
        this.accessToken = accessToken;
        this.allCategories = allTypes;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<Category> getAllCategories() {
        return allCategories;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"categories\":[ ";
        response = allCategories.stream().map((Category type) -> type.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
