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
public class SuperCategorySuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<SuperCategory> allSuperCategories;

    public SuperCategorySuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public SuperCategorySuccessResponse(String status, String accessToken, ArrayList<SuperCategory> allSuperTypes) {
        this.status = status;
        this.accessToken = accessToken;
        this.allSuperCategories = allSuperTypes;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<SuperCategory> getAllSuperCategories() {
        return allSuperCategories;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"superCategories\":[ ";
        response = allSuperCategories.stream().map((SuperCategory type) -> type.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
