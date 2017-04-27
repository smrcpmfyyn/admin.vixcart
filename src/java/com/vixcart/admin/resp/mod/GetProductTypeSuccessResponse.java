package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetProductTypeSuccessResponse {

    private final String status;
    private final String accessToken;
    private final ProductType res;

    public GetProductTypeSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.res = new ProductType();
    }

    public GetProductTypeSuccessResponse(String status, String accessToken, ProductType res) {
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
        String response = "";
        if (res.getId().equals("invalid")) {
            response = "{\"status\":\"" + status + "\"}";
        } else {
            response = "{\"status\":\"" + status + "\",\"ad\":" + res.toString();
            response += "}";
        }
        return response;
    }
}
