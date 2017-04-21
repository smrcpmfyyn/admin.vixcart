/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.result.ResetAffiliateUserResult;

/**
 * @company techvay
 * @author rifaie
 */
public class ResetAffiliateUserFailureResponse {

    private final ResetAffiliateUserResult reqR;
    private final String error;

    public ResetAffiliateUserFailureResponse(ResetAffiliateUserResult reqR, String error) {
        this.reqR = reqR;
        this.error = error;
    }

    @Override
    public String toString() {
        String json = "\"status\":\"" + ResponseMsg.RESP_NOT_OK + "\",";
        String[] errors = error.split("#");
        String resp;
        String param;
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[1];
            switch (parameter) {
                case "at":
                    param = reqR.getAt();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "admintype":
                    String admType = reqR.getAdmintype();
                    resp = admType.substring(admType.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "uid":
                    param = reqR.getUid();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
            }
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }

}

