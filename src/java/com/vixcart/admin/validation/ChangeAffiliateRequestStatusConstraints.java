/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.ChangeAffiliateRequestStatusValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.ChangeAffiliateRequestStatus;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @company techvay
 * @author rifaie
 */
public class ChangeAffiliateRequestStatusConstraints implements ChangeAffiliateRequestStatusValidator {

    private final ChangeAffiliateRequestStatus req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public ChangeAffiliateRequestStatusConstraints(ChangeAffiliateRequestStatus req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateRequestId() throws Exception {
        String valid = ErrMsg.ERR_REQ_ID;
        String regX = RegX.REGX_DIGIT;
        String reqId = req.getRequest_id();
        if (validate(reqId, regX)) {
            if (dbc.checkreqId(reqId)) {
                valid = CorrectMsg.CORRECT_COMPANY;
            } else {
                valid = ErrMsg.ERR_REQ_ID_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateStatus() throws Exception {
        String valid = ErrMsg.ERR_STATUS;
        String regX = RegX.REGX_DIGIT;
        String status = req.getStatus();
        if (validate(status, regX)) {
            if(status.matches("2")||status.matches("3")){
                valid = CorrectMsg.CORRECT_STATUS;
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
}


