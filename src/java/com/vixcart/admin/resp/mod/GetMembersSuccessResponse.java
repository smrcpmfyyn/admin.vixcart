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
public class GetMembersSuccessResponse {
    private final String status;
    private final String accessToken;
    private final ArrayList<MemberDetails> members;
    private final String queryStatus;

    public GetMembersSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.members = new ArrayList<>();
        this.queryStatus = "empty";
    }

    public GetMembersSuccessResponse(String status, String accessToken, ArrayList<MemberDetails> members) {
        this.status = status;
        this.accessToken = accessToken;
        this.members = members;
        this.queryStatus = "available";
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<MemberDetails> getMembers() {
        return members;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"qs\":\""+queryStatus +"\",\"mds\":[ ";
        response = members.stream().map((MemberDetails md) -> md+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
