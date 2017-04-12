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
import com.vixcart.admin.req.mod.UpdateAffiliate;
import java.sql.SQLException;
import java.util.HashSet;
import com.vixcart.admin.intfc.validation.UpdateAffiliateValidator;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateAffiliateConstraints implements UpdateAffiliateValidator {

    private final UpdateAffiliate req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public UpdateAffiliateConstraints(UpdateAffiliate req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateCkCompany() throws Exception {
        String valid = ErrMsg.ERR_CK_COMPANY;
        String regX = RegX.REGX_COMPANY;
        String company = req.getCompany();
        if (validate(company, regX)) {
            if (dbc.checkCompany(company) == 1) {
                if (company.equals(req.getCompany())) {
                    valid = CorrectMsg.CORRECT_CK_COMPANY;
                } else {
                    valid = ErrMsg.ERR_CK_COMPANY_NOT_MATCHES;
                }
            } else {
                valid = ErrMsg.ERR_CK_COMPANY_NOT_EXISTS;
            }
        }
        return valid;
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
    public String validateStatus() throws Exception {
        String valid = ErrMsg.ERR_STATUS;
        String regX = RegX.REGX_DIGIT;
        String status = req.getStatus();
        if (validate(status, regX)) {
            int st = Integer.parseInt(status);
            if (st == 1 || st == 2) {
                valid = CorrectMsg.CORRECT_STATUS;
            } else {
                valid = ErrMsg.ERR_STATUS_NOT_EXISTS;
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

    @Override
    public String validateMPP() throws Exception {
        String valid = ErrMsg.ERR_MPP;
        String regX = RegX.REGX_DIGIT;
        String mpp = req.getMpp();
        if (validate(mpp, regX)) {
            int maxpp = Integer.parseInt(mpp);
            if (maxpp > 100000) {
                valid = CorrectMsg.CORRECT_MPP;
            } else {
                valid = ErrMsg.ERR_MPP_LESS;
            }
        }
        return valid;
    }

    @Override
    public String validateCF() throws Exception {
        String valid = ErrMsg.ERR_CF;
        String regX = RegX.REGX_FLOAT;
        String cf = req.getCf();
        if (validate(cf, regX)) {
            valid = CorrectMsg.CORRECT_CF;
        }
        return valid;
    }

}
