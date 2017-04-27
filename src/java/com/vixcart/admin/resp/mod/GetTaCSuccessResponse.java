package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetTaCSuccessResponse {

    private final String status;
    private final String accessToken;
    private final TaC res;

    public GetTaCSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.res = new TaC();
    }

    public GetTaCSuccessResponse(String status, String accessToken, TaC res) {
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
