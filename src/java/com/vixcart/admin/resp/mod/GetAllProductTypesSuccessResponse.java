package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
import java.util.ArrayList;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetAllProductTypesSuccessResponse {

    private final String status;
    private String accessToken;
    private ArrayList<ProductType> res;

    public GetAllProductTypesSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public GetAllProductTypesSuccessResponse(String status, String accessToken, ArrayList<ProductType> res) {
        this.accessToken = accessToken;
        this.res = res;
        this.status = status;
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
        response = res.stream().map((ProductType type) -> type.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
