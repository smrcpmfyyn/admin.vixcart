package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">

import java.util.ArrayList;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetAllTaCSuccessResponse {

    private final String status;
    private final String accessToken;
    private ArrayList<TaC> res;

    public GetAllTaCSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.res = new ArrayList<>();
    }

    public GetAllTaCSuccessResponse(String status, String accessToken, ArrayList<TaC> res) {
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
          String response = "{\"status\":\""+status + "\",\"tacs\":[ ";
        response = res.stream().map((TaC type) -> type.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
  }
}
