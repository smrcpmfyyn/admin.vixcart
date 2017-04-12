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
public class GetPinSuccessResponse {
    private final String status;
    private final String accessToken;
    private final ArrayList<String> allPin;

    public GetPinSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allPin = new ArrayList<>();
    }

    public GetPinSuccessResponse(String status, String accessToken, ArrayList<String> allPin) {
        this.status = status;
        this.accessToken = accessToken;
        this.allPin = allPin;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<String> getAllPin() {
        return allPin;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"pins\":[ ";
        response = allPin.stream().map((String type) -> type+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
