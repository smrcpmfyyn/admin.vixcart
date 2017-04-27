package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">

import java.util.ArrayList;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetAllBrandsSuccessResponse {

    private final String status;
    private String accessToken;
    private ArrayList<Brand> res;

    public GetAllBrandsSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.res = new ArrayList<>();
    }

    public GetAllBrandsSuccessResponse(String status, String accessToken, ArrayList<Brand> res) {
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
         String response = "{\"status\":\""+status + "\",\"brands\":[ ";
        response = res.stream().map((Brand type) -> type.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
