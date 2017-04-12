/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class GetNoOfMembersSuccessResponse {
    private final String status;
    private final String accessToken;
    private int noOfMembers;

    public GetNoOfMembersSuccessResponse(String status, String accessToken, int noOfMembers) {
        this.status = status;
        this.accessToken = accessToken;
        this.noOfMembers = noOfMembers;
    }

    public GetNoOfMembersSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getNoOfMembers() {
        return noOfMembers;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"noOfMembers\":\""+noOfMembers+"\"}";
        return response;
    }
}
