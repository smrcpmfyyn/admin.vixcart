/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.GetPin;

/**
 * @company techvay
 * @author rifaie
 */
public class GetPinValidation implements Validation{
    
    private final GetPin req;
    private String paramValue = "";
    private String paramName = "";

    public GetPinValidation(GetPin gp) {
        this.req = gp;
    }

    @Override
    public void validation() throws Exception {
        GetPinConstraints gpc = new GetPinConstraints(req);
        String valid = "";
        valid += gpc.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            valid += "#" + gpc.validateUserType("management");
        }
        gpc.closeConnection();
        int count = 0;
        for (String str : valid.split("#")) {
            paramName += str.split(" ")[1].toLowerCase() + "#";
            if (!str.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                count++;
                paramValue += str + "#";
            } else {
                paramValue += CorrectMsg.CORRECT_MESSAGE + "#";
            }
        }
        paramName += "gpValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_USER;
        } else {
            paramValue += ErrMsg.ERR_USER;
        }
    }
    
    @Override
    public String toString() {
        String[] paramsN = paramName.split("#");
        String[] paramV = paramValue.split("#");
        String json = "";
        for (int i = 0; i < paramsN.length; i++) {
            json += "\"" + paramsN[i] + "\"" + ":" + "\"" + paramV[i] + "\" ,";
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }

}
