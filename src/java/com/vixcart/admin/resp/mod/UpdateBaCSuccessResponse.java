package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateBaCSuccessResponse {

    private final String status;
    private final String accessToken;

    public UpdateBaCSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "{\"status\":\"" + status + "\"}";
    }
}
