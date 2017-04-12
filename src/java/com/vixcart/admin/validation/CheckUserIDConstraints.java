/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.CheckUserIDValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.CheckUserID;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @company techvay
 * @author rifaie
 */
public class CheckUserIDConstraints implements CheckUserIDValidator {

    private final CheckUserID cuid;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public CheckUserIDConstraints(CheckUserID cuid) throws Exception {
        this.cuid = cuid;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateUserSlNo() throws Exception {
        String valid = ErrMsg.ERR_UID;
        String regX = RegX.REGX_DIGIT;
        String uid = cuid.getUid();
        if (validate(uid, regX)) {
            if (dbc.checkSerialNumber(uid) == 1) {
                valid = CorrectMsg.CORRECT_UID;
            } else {
                valid = ErrMsg.ERR_UID_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = cuid.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        AdminID admin = mdbc.getAdminID(at);
        if (!admin.getProfile_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            if (dbc.checkNBAdminID(admin.getProfile_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                cuid.setAdmin_id(admin.getProfile_id());
                cuid.setUtype(admin.getType());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public String validateUserType(String adminType) throws Exception {
        String valid = ErrMsg.ERR_ADMIN_TYPE;
        HashSet<String> types = dbc.getSubTypes(cuid.getUtype());
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
