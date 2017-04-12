/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.EditUserValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.mongo.mod.UUA;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.EditUser;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @company techvay
 * @author rifaie
 */
public class EditUserConstraints implements EditUserValidator {

    private final EditUser eu;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public EditUserConstraints(EditUser eu) throws Exception {
        this.eu = eu;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateUID() throws Exception {
        String valid = ErrMsg.ERR_UID;
        String regX = RegX.REGX_DIGIT;
        String uid = eu.getId();
        UUA uua = mdbc.getUpdateUserActivity(eu.getAdmin_id());
        if (validate(uid, regX)) {
            if (dbc.checkSerialNumber(uid) == 1) {
                if (uua.getUpdate_user_id().equals(eu.getId())) {
                    valid = CorrectMsg.CORRECT_UID;
                    eu.setUpdatedAdminId(dbc.getAdminID(uid));
                } else {
                    valid = ErrMsg.ERR_UID_REQUEST;
                }
            } else {
                valid = ErrMsg.ERR_UID_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateName() throws Exception {
        String valid = ErrMsg.ERR_NAME;
        String regX = RegX.REGX_STRING;
        String name = eu.getName();
        if (validate(name, regX)) {
            valid = CorrectMsg.CORRECT_NAME;
        }
        return valid;
    }

    @Override
    public String validateDesignation() throws Exception {
        String valid = ErrMsg.ERR_DESIGNATION;
        String regX = RegX.REGX_STRING;
        String designation = eu.getDesignation();
        if (validate(designation, regX)) {
            valid = CorrectMsg.CORRECT_DESIGNATION;
        }
        return valid;
    }

    @Override
    public String validateAddress1() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS1;
        String regX = RegX.REGX_STRING;
        String add1 = eu.getAddress1();
        if (validate(add1, regX)) {
            valid = CorrectMsg.CORRECT_ADDRESS1;
        }
        return valid;
    }

    @Override
    public String validateAddress2() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS2;
        String regX = RegX.REGX_NULL_STRING;
        String add2 = eu.getAddress2();
        if (validate(add2, regX)) {
            valid = CorrectMsg.CORRECT_ADDRESS2;
        }
        return valid;
    }

    @Override
    public String validatePlace() throws Exception {
        String valid = ErrMsg.ERR_PLACE;
        String regX = RegX.REGX_STRING;
        String place = eu.getPlace();
        if (validate(place, regX)) {
            valid = CorrectMsg.CORRECT_PLACE;
        }
        return valid;
    }

    @Override
    public String validatePin() throws Exception {
        String valid = ErrMsg.ERR_PIN;
        String regX = RegX.REGX_DIGIT;
        String pin = eu.getPin();
        if (validate(pin, regX)) {
            if (mdbc.checkPin(pin)) {
                valid = CorrectMsg.CORRECT_PIN;
            }
        }
        return valid;
    }

    @Override
    public String validateUtype() throws Exception {
        String valid = ErrMsg.ERR_SUPER_TYPE;
        String regX = RegX.REGX_SUPER_TYPE;
        String type = eu.getUtype();
        if (validate(type, regX)) {
            if (dbc.checkSuperType(type) == 1) {
                valid = CorrectMsg.CORRECT_TYPE_ID;
                eu.setUserType(dbc.getType(type));
            } else {
                valid = ErrMsg.ERR_TYPE_ID_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = eu.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        AdminID admin = mdbc.getAdminID(at);
        if (!admin.getProfile_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            if (dbc.checkNBAdminID(admin.getProfile_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                eu.setAdmin_id(admin.getProfile_id());
                eu.setType(admin.getType());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public String validateUserType(String adminType) throws Exception {
        String valid = ErrMsg.ERR_ADMIN_TYPE;
        HashSet<String> types = dbc.getSubTypes(eu.getType());
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

}
