/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.GetTaCs;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetTaCsValidation implements Validation{
    private final GetTaCs req;
    private String paramValue = "";
    private String paramName = "";

    public GetTaCsValidation(GetTaCs req) {
        this.req = req;
    }

    @Override
    public void validation() throws Exception {
        GetTaCsConstraints reqC = new GetTaCsConstraints(req);
        String valid = "";
        valid += reqC.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            valid += "#" + reqC.validateUserType("product management");
        }
        reqC.closeConnection();
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
        paramName += "reqValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_GET_TACS;
        } else {
            paramValue += ErrMsg.ERR_GET_TACS;
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
