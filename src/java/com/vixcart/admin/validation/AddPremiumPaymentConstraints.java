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
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.AddPremiumPayment;
import java.sql.SQLException;
import java.util.HashSet;
import com.vixcart.admin.intfc.validation.AddPremiumPaymentValidator;

/**
 * @company techvay
 * @author rifaie
 */
public class AddPremiumPaymentConstraints implements AddPremiumPaymentValidator {

    private final AddPremiumPayment req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public AddPremiumPaymentConstraints(AddPremiumPayment req) throws Exception {
        this.req = req;
        this.dbc = DB.getConnection();
        this.mdbc = DB.getMongoConnection();
    }

    @Override
    public String validateCompany() throws Exception {
        String valid = ErrMsg.ERR_COMPANY;
        String regX = RegX.REGX_COMPANY;
        String company = req.getCompany();
        if (validate(company, regX)) {
            if (dbc.checkCompany(company) == 1) {
                valid = CorrectMsg.CORRECT_COMPANY;
            } else {
                valid = ErrMsg.ERR_COMPANY_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateReferenceNo() throws Exception {
        String valid = ErrMsg.ERR_REFERENCE_NO;
        String regX = RegX.REGX_REFERENCE_NO;
        String ref = req.getReferenceNo();
        if (validate(ref, regX)) {
            valid = CorrectMsg.CORRECT_REFERENCE_NO;
        }
        return valid;
    }

    @Override
    public String validateAmount() throws Exception {
        String valid = ErrMsg.ERR_AMOUNT;
        String regX = RegX.REGX_DIGIT;
        String amount = req.getAmount();
        if (validate(amount, regX)) {
            valid = CorrectMsg.CORRECT_AMOUNT;
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
                req.setUtype(admin.getType());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public String validateUserType(String adminType) throws Exception {
        String valid = ErrMsg.ERR_ADMIN_TYPE;
        HashSet<String> types = dbc.getSubTypes(req.getUtype());
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
