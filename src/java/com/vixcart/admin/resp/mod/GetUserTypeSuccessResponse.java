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
public class GetUserTypeSuccessResponse {
    private final String status;
    private final String accessToken;
    private final ArrayList<UserType1> allTypes;

    public GetUserTypeSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allTypes = new ArrayList<>();
    }

    public GetUserTypeSuccessResponse(String status, String accessToken, ArrayList<UserType1> allTypes) {
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

    public ArrayList<UserType1> getAllTypes() {
        return allTypes;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"types\":[";
        response = allTypes.stream().map((UserType1 type) -> type.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
