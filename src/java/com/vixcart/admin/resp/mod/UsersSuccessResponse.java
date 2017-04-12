/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * @company techvay
 * @author rifaie
 */
public class UsersSuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<User> allUsers;

    public UsersSuccessResponse(String status, String accessToken, ArrayList<User> allUsers) {
        this.status = status;
        this.accessToken = accessToken;
        this.allUsers = allUsers;
    }

    public UsersSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allUsers = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"users\":[";
        response = allUsers.stream().map((User user) -> user.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
    
    
}
