/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.RPValidator;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.VerifyToken;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.ResetPassword;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class RPConstraints implements RPValidator{
    
    private final ResetPassword rp;
    private final MongoConnect mdbc;

    public RPConstraints(ResetPassword rp) throws Exception {
        this.rp = rp;
        mdbc = DB.getMongoConnection();
    }

    @Override
    public String validateToken() throws Exception {
        String valid = ErrMsg.ERR_TOKEN;
        String regX = RegX.REGX_TOKEN;
        String token = rp.getToken();
        VerifyToken vt = mdbc.verifyToken(token);
        if (validate(token, regX)) {
            valid = vt.getValidation();
        }
        return valid;
    }

    @Override
    public boolean validate(String value, String regX) {
        boolean valid = false;
        System.out.println(value);
        System.out.println(regX);
        if (value.matches(regX)) {
            valid = true;
        }
        return valid;
    }

    @Override
    public void closeConnection() throws SQLException {
        mdbc.closeConnection();
    }

}
