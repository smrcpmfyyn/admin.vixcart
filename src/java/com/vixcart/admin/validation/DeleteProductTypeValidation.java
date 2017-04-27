/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.validation;

import com.vixcart.admin.intfc.validation.Validation;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.req.mod.DeleteProductType;

/**
 * @company techvay
 * @author rifaie
 */
public class DeleteProductTypeValidation implements Validation {

    private final DeleteProductType req;
    private String paramValue = "";
    private String paramName = "";

    public DeleteProductTypeValidation(DeleteProductType au) {
        this.req = au;
    }

    @Override
    public void validation() throws Exception {
        DeleteProductTypeConstraints auc = new DeleteProductTypeConstraints(req);
        String valid = "";
        valid += auc.validateAccessToken();
        if (valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            String valid1 = auc.validateUserType("product management");
            valid += "#" + valid1;
            if (valid1.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                valid += "#" + auc.validateProductType();
            }
        }
        auc.closeConnection();
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
            paramValue += CorrectMsg.CORRECT_DELETE_PTYPE;
        } else {
            paramValue += ErrMsg.ERR_DELETE_PTYPE;
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
