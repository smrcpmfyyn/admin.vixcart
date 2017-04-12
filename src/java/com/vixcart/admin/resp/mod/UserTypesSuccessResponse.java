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
public class UserTypesSuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<UserType> allTypes;

    public UserTypesSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allTypes = new ArrayList<>();
    }

    public UserTypesSuccessResponse(String status, String accessToken, ArrayList<UserType> allTypes) {
        this.status = status;
        this.accessToken = accessToken;
        this.allTypes = allTypes;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<UserType> getAllTypes() {
        return allTypes;
    }

    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"types\":[";
        response = allTypes.stream().map((UserType type) -> type.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
    
    
}
