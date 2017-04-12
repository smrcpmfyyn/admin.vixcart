/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.intfc.validation.LoginValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.Login;
import java.sql.SQLException;
import java.util.List;

/**
 * @company techvay
 * @author rifaie
 */
public final class LoginConstraints implements LoginValidator {

    private final Login log;
    private final DBConnect dbc;

    /**
     *
     * @param log
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public LoginConstraints(Login log) throws ClassNotFoundException, SQLException {
        this.log = log;
        dbc = DB.getConnection();
    }

    @Override
    public String validateUserName() throws SQLException {
        String valid = ErrMsg.ERR_UNAME;
        String regX = RegX.REGX_ADMIN_UNAME;
        String uname = log.getuName();
        if (validate(uname, regX)) {
            if (dbc.checkUname(uname) == 1) {
                if (dbc.checkNBUname(uname) == 1) {
                    valid = CorrectMsg.CORRECT_UNAME;
                } else {
                    valid = ErrMsg.ERR_UNAME_BLOCKED;
                }
            } else {
                valid = ErrMsg.ERR_UNAME_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validatePassword() throws Exception {
        String valid = ErrMsg.ERR_PASSWORD;
        String regX = RegX.REGX_B64ENCODE;
        String uname = log.getuName();
        List<String> passDSalt = dbc.getPassDSalt(uname);
        log.setSalt(passDSalt.get(0));
        log.changePassword();
        log.setAdmin_id(passDSalt.get(2));
        log.setType(passDSalt.get(3));
        String apassword = passDSalt.get(1);
        String password = log.getPassword();
        if (validate(password, regX)) {
            if (apassword.equals(password)) {
                valid = CorrectMsg.CORRECT_PASSWORD;
            }
        }
        return valid;
    }

    @Override
    public boolean validate(String value, String regX) {
        boolean valid = false;
        if (value.matches(regX)) {
            valid = true;
        }
        return valid;
    }

    @Override
    public void closeConnection() throws SQLException {
        dbc.closeConnection();
    }

}
