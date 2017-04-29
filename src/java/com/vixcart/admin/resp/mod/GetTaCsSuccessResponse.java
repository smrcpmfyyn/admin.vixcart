/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">

import java.util.ArrayList;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetTaCsSuccessResponse {

    private final String status;
    private final String accessToken;
    private final ArrayList<TaC> res;

    public GetTaCsSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.res = new ArrayList<>();
    }

    public GetTaCsSuccessResponse(String status, String accessToken, ArrayList<TaC> res) {
        this.status = status;
        this.accessToken = accessToken;
        this.res = res;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"productTypes\":[ ";
        response = res.stream().map((TaC type) -> type.getTaCs()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
