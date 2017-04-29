package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">

import java.util.ArrayList;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetBPaCsSuccessResponse {

    private final String status;
    private final String accessToken;
    private ArrayList<BPaC> res;

    public GetBPaCsSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.res = new ArrayList<>();
    }

    public GetBPaCsSuccessResponse(String status, String accessToken, ArrayList<BPaC> res) {
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
        response = res.stream().map((BPaC bpac) -> bpac.getBPaCs()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
