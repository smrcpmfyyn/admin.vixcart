package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetSuperCategorySuccessResponse {

    private final String status;
    private final String accessToken;
    private final SuperCategory res ;

    public GetSuperCategorySuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        res = new SuperCategory("invalid", "invalid", "invalid", "invalid");
    }

    public GetSuperCategorySuccessResponse(String status, String accessToken, SuperCategory res) {
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
        if (res.getSuper_category_id().equals("invalid")) {
            response = "{\"status\":\"" + status + "\"}";
        } else {
            response = "{\"status\":\"" + status + "\",\"superCateg\":" + res.toString();
            response += "}";
        }
        return response;
    }
}
