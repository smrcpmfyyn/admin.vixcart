/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.mongo.mod.UTA;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.UpdateUserType;
import java.sql.SQLException;
import java.util.HashSet;
import com.vixcart.admin.intfc.validation.UpdateUserTypeValidator;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateUserTypeConstraints implements UpdateUserTypeValidator {

    private final UpdateUserType updateTyp;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public UpdateUserTypeConstraints(UpdateUserType updateTyp) throws Exception {
        this.updateTyp = updateTyp;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateTypeID() throws Exception {
        String valid = ErrMsg.ERR_TYPE_ID;
        String regX = RegX.REGX_SUPER_TYPE;
        String typeID = updateTyp.getTypeid();
        UTA uta = mdbc.getUpdateTypeActivity(updateTyp.getAdmin_id());
        if (validate(typeID, regX)) {
            if (dbc.checkSuperType(typeID) == 1) {
                if (uta.getUpdate_type_id().equals(updateTyp.getTypeid())) {
                    valid = CorrectMsg.CORRECT_TYPE_ID;
                } else {
                    valid = ErrMsg.ERR_TYPE_ID_REQUEST;
                }
            } else {
                valid = ErrMsg.ERR_TYPE_ID_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateType() throws Exception {
        String valid = ErrMsg.ERR_TYPE;
        String regX = RegX.REGX_TYPE;
        String type = updateTyp.getType();
        if (validate(type, regX)) {
            if (dbc.checkType(type) == 1) {
                if (dbc.checkTypeWithID(type, updateTyp.getTypeid()) == 1) {
                    valid = CorrectMsg.CORRECT_TYPE;
                }else{
                    valid = ErrMsg.ERR_TYPE_MISMATCH;
                }
            } else {
                valid = ErrMsg.ERR_TYPE_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateSuperType() throws Exception {
        String valid = ErrMsg.ERR_SUPER_TYPE;
        String regX = RegX.REGX_SUPER_TYPE;
        String superType = updateTyp.getSuperType();
        if (validate(superType, regX)) {
            if (dbc.checkSuperType(superType) == 1) {
                if (dbc.checkSubtypeSuperType(superType, updateTyp.getTypeid())) {
                    valid = CorrectMsg.CORRECT_SUPER_TYPE;
                } else {
                    valid = ErrMsg.ERR_SUPER_TYPE_SUB_TYPE;
                }
            } else {
                valid = ErrMsg.ERR_SUPER_TYPE_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = updateTyp.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        AdminID admin = mdbc.getAdminID(at);
        if (!admin.getProfile_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            if (dbc.checkNBAdminID(admin.getProfile_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                updateTyp.setAdmin_id(admin.getProfile_id());
                updateTyp.setUtype(admin.getType());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public String validateUserType(String adminType) throws Exception {
        String valid = ErrMsg.ERR_ADMIN_TYPE;
        HashSet<String> types = dbc.getSubTypes(updateTyp.getUtype());
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
