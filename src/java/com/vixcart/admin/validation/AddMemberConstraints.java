/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.AddMemberValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.AddMember;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @company techvay
 * @author rifaie
 */
public class AddMemberConstraints implements AddMemberValidator {

    private final AddMember req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public AddMemberConstraints(AddMember au) throws Exception {
        this.req = au;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateMType() throws Exception {
        String valid = ErrMsg.ERR_MTYPE;
        String regX = RegX.REGX_DIGIT;
        String mtype = req.getmType();
        if (validate(mtype, regX)) {
            if (mtype.equals("1")||mtype.equals("2")||mtype.equals("3")) {
                valid = CorrectMsg.CORRECT_MTYPE;
            } 
        }
        return valid;
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
    public String validateMobile() throws Exception {
        String valid = ErrMsg.ERR_MOBILE;
        String regX = RegX.REGX_MOBILE;
        String mobile = req.getMobile();
        if (validate(mobile, regX)) {
            if (dbc.checkMemberMobile(mobile)) {
                valid = CorrectMsg.CORRECT_MOBILE;
            } else {
                valid = ErrMsg.ERR_MOBILE_EXISTS;
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
            if (dbc.checkMemberEmail(email)) {
                req.setNew_member_id(dbc.getNewMemberId());
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
        mdbc.closeConnection();
    }

}

