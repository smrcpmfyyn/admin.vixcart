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
public class SearchMemberIDsSuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<MemberID> mids;

    public SearchMemberIDsSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.mids = new ArrayList<>();
    }

    public SearchMemberIDsSuccessResponse(String status, String accessToken, ArrayList<MemberID> mids) {
        this.status = status;
        this.accessToken = accessToken;
        this.mids = mids;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<MemberID> getMemberIDs() {
        return mids;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"affiliates\":[ ";
        response = mids.stream().map((MemberID mid) -> mid.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
