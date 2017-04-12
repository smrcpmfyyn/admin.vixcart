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
public class GetUserIdsSuccessResponse {
    
    private final String status;
    private final String accessToken;
    private UserIds uids;

    public GetUserIdsSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.uids = new UserIds();
    }

    public GetUserIdsSuccessResponse(String status, String accessToken, UserIds uids) {
        this.status = status;
        this.accessToken = accessToken;
        this.uids = uids;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public UserIds getUids() {
        return uids;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\","+uids.toString();
        response += "}";
        return response;
    }

}
