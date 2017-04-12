/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.GetAffiliateUsersValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.GetAffiliateUsers;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @company techvay
 * @author rifaie
 */
public class GetAffiliateUsersConstraints implements GetAffiliateUsersValidator {

    private final GetAffiliateUsers req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public GetAffiliateUsersConstraints(GetAffiliateUsers req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateQuery() throws Exception {
        String valid = ErrMsg.ERR_QUERY;
        String regX = RegX.REGX_COMPANY;
        String query = req.getQuery();
        if (validate(query, regX)) {
            String queryType = mdbc.getSearchAffiliateQueryType(query);
            if (!queryType.equals("nothing")) {
                valid = CorrectMsg.CORRECT_QUERY;
                req.setQueryType(queryType);
            } else {
                valid = ErrMsg.ERR_QUERY_NO_RESULT;
            }
        }
        return valid;
    }

    @Override
    public String validateOffset() throws Exception {
        String valid = ErrMsg.ERR_OFFSET;
        String regx = RegX.REGX_DIGIT;
        if (validate(req.getPageNo(), regx)) {
            if (validate(req.getMaxEntries(), regx)) {
                valid = CorrectMsg.CORRECT_OFFSET;
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
}
