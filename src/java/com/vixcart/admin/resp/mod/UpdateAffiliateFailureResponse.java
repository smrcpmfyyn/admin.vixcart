/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.result.UpdateAffiliateResult;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateAffiliateFailureResponse {
    private final UpdateAffiliateResult reqR;
    private final String error;

    public UpdateAffiliateFailureResponse(UpdateAffiliateResult updateUTR, String error) {
        this.reqR = updateUTR;
        this.error = error;
    }
    
    @Override
    public String toString() {
        String json = "\"status\":\""+ResponseMsg.RESP_NOT_OK + "\",";
        String[] errors = error.split("#");
        
        String resp;
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[i];
            switch (parameter) {
                case "at":
                    String at = reqR.getAt();
                    resp = at.substring(at.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "admintype":
                    String admType = reqR.getAdmintype();
                    resp = admType.substring(admType.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "ckcompany":
                    String ckcompany = reqR.getCkcompany();
                    resp = ckcompany.substring(ckcompany.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "comapany":
                    String company = reqR.getCompany();
                    resp = company.substring(company.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "status":
                    String status = reqR.getStatus();
                    resp = status.substring(status.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "mpp":
                    String mpp = reqR.getMpp();
                    resp = mpp.substring(mpp.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "cf":
                    String cf = reqR.getCf();
                    resp = cf.substring(cf.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
            }
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }
}
