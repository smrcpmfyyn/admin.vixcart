package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetSubCategorySuccessResponse {

    private final String status;
    private final String accessToken;
    private final SubCategory res;

    public GetSubCategorySuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        res = new SubCategory("invalid", "invalid", "invalid", "invalid", "invalid");
    }

    public GetSubCategorySuccessResponse(String status, String accessToken, SubCategory res) {
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
        if (res.getSub_category_id().equals("invalid")) {
            response = "{\"status\":\"" + status + "\"}";
        } else {
            response = "{\"status\":\"" + status + "\",\"subCategory\":" + res.toString();
            response += "}";
        }
        return response;
    }
}
