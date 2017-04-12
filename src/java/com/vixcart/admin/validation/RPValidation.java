/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.ResetPassword;

/**
 * @company techvay
 * @author rifaie
 */
public class RPValidation implements Validation{
    
    private final ResetPassword rp;
    private String paramValue = "";
    private String paramName = "";

    public RPValidation(ResetPassword ve) {
        this.rp = ve;
    }

    @Override
    public void validation() throws Exception {
        RPConstraints rpc = new RPConstraints(rp);
        String valid = "";
        valid += rpc.validateToken();
        int count = 0;
        paramName += valid.split(" ")[1].toLowerCase() + "#";
        if (!valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            paramValue += valid + "#";
            count++;
        } else {
            paramValue = CorrectMsg.CORRECT_MESSAGE + "#";
        }
        paramName += "rPValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_RP;
        } else {
            paramValue += ErrMsg.ERR_RP;
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
