/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.AddPremiumPayment;

/**
 * @company techvay
 * @author rifaie
 */
public class AddPremiumPaymentValidation implements Validation {
    private final AddPremiumPayment addTyp;
    private String paramValue = "";
    private String paramName = "";

    public AddPremiumPaymentValidation(AddPremiumPayment addTyp) {
        this.addTyp = addTyp;
    }

    @Override
    public void validation() throws Exception {
        AddPremiumPaymentConstraints addTypc = new AddPremiumPaymentConstraints(addTyp);
        String valid = "";
        valid += addTypc.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            String valid1 = addTypc.validateUserType("affiliate");
            valid += "#" + valid1;
            if(valid1.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                valid += "#"+addTypc.validateCompany();
                valid += "#"+addTypc.validateReferenceNo();
                valid += "#"+addTypc.validateAmount();
            }
        }
        addTypc.closeConnection();
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
            paramValue += CorrectMsg.CORRECT_ADD_PP;
        } else {
            paramValue += ErrMsg.ERR_ADD_PP;
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

