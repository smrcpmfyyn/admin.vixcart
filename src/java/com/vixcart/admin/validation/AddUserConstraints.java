/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.AddUserValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.AddUser;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @company techvay
 * @author rifaie
 */
public class AddUserConstraints implements AddUserValidator {

    private final AddUser req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public AddUserConstraints(AddUser au) throws Exception {
        this.req = au;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateName() throws Exception {
        String valid = ErrMsg.ERR_NAME;
        String regX = RegX.REGX_STRING;
        String name = req.getName();
        if (validate(name, regX)) {
            valid = CorrectMsg.CORRECT_NAME;
        }
        return valid;
    }

    @Override
    public String validateAddress1() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS1;
        String regX = RegX.REGX_STRING;
        String add1 = req.getAddress1();
        if (validate(add1, regX)) {
            valid = CorrectMsg.CORRECT_ADDRESS1;
        }
        return valid;
    }

    @Override
    public String validateAddress2() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS2;
        String regX = RegX.REGX_NULL_STRING;
        String add2 = req.getAddress2();
        if (validate(add2, regX)) {
            valid = CorrectMsg.CORRECT_ADDRESS2;
        }
        return valid;
    }

    @Override
    public String validatePlace() throws Exception {
        String valid = ErrMsg.ERR_PLACE;
        String regX = RegX.REGX_STRING;
        String place = req.getPlace();
        if (validate(place, regX)) {
            valid = CorrectMsg.CORRECT_PLACE;
        }
        return valid;
    }

    @Override
    public String validatePin() throws Exception {
        String valid = ErrMsg.ERR_PIN;
        String regX = RegX.REGX_DIGIT;
        String pin = req.getPin();
        if (validate(pin, regX)) {
            if (mdbc.checkPin(pin)) {
                valid = CorrectMsg.CORRECT_PIN;
            }
        }
        return valid;
    }

    @Override
    public String validateUtype() throws Exception {
        String valid = ErrMsg.ERR_TYPE_ID;
        String regX = RegX.REGX_SUPER_TYPE;
        String type = req.getUtype();
        if (validate(type, regX)) {
            if (dbc.checkSuperType(type) == 1) {
                valid = CorrectMsg.CORRECT_TYPE_ID;
                req.setUserType(dbc.getType(type));
            } else {
                valid = ErrMsg.ERR_TYPE_ID_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateUName() throws Exception {
        String valid = ErrMsg.ERR_UNAME;
        String regX = RegX.REGX_ADMIN_UNAME;
        String uname = req.getUname();
        if (validate(uname, regX)) {
            if (dbc.checkUname(uname) == 0) {
                valid = CorrectMsg.CORRECT_UNAME;
            } else {
                valid = ErrMsg.ERR_UNAME_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateEmail() throws Exception {
        String valid = ErrMsg.ERR_EMAIL;
        String regX = RegX.REGX_EMAIL;
        String email = req.getEmail();
        if (validate(email, regX)) {
            if (dbc.checkEmail(email) == 0) {
                req.setNew_admin_id(dbc.getNewAdminID());
                valid = CorrectMsg.CORRECT_EMAIL;
            } else {
                valid = ErrMsg.ERR_EMAIL_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = req.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        AdminID admin = mdbc.getAdminID(at);
        if (!admin.getProfile_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            if (dbc.checkNBAdminID(admin.getProfile_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                req.setAdmin_id(admin.getProfile_id());
                req.setType(admin.getType());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public String validateUserType(String adminType) throws Exception {
        String valid = ErrMsg.ERR_ADMIN_TYPE;
        HashSet<String> types = dbc.getSubTypes(req.getType());
        if (types.contains(adminType)) {
            valid = CorrectMsg.CORRECT_ADMIN_TYPE;
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

    @Override
    public String validateDesignation() throws Exception {
        String valid = ErrMsg.ERR_DESIGNATION;
        String regX = RegX.REGX_STRING;
        String designation = req.getDesignation();
        if (validate(designation, regX)) {
            valid = CorrectMsg.CORRECT_DESIGNATION;
        }
        return valid;
    }

}
