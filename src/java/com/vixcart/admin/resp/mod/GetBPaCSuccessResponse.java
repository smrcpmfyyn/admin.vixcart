package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetBPaCSuccessResponse {

    private final String  status;
    private final String accessToken;
    private final BPaC res;

    public GetBPaCSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.res = new BPaC();
    }

    public GetBPaCSuccessResponse(String status, String accessToken, BPaC res) {
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
        return "{\"status\":\"" + status + "\"}";
    }
}
