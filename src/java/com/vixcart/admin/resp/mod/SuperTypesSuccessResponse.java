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
public class SuperTypesSuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<SuperUserType> allSuperTypes;

    public SuperTypesSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allSuperTypes = new ArrayList<>();
    }

    public SuperTypesSuccessResponse(String status, String accessToken, ArrayList<SuperUserType> allSuperTypes) {
        this.status = status;
        this.accessToken = accessToken;
        this.allSuperTypes = allSuperTypes;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<SuperUserType> getAllSuperTypes() {
        return allSuperTypes;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"superTypes\":[";
        response = allSuperTypes.stream().map((SuperUserType type) -> type.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
