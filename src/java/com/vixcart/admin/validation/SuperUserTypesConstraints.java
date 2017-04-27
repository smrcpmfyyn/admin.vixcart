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
import com.vixcart.admin.req.mod.SuperUserTypes;
import java.sql.SQLException;
import java.util.HashSet;
import com.vixcart.admin.intfc.validation.SuperUserTypesValidator;

/**
 * @company techvay
 * @author rifaie
 */
public class SuperUserTypesConstraints implements SuperUserTypesValidator{
    
    private final SuperUserTypes type;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public SuperUserTypesConstraints(SuperUserTypes type) throws Exception {
        this.type = type;
        this.dbc = DB.getConnection();
        this.mdbc = DB.getMongoConnection();
    }
    
    

    @Override
    public String validateAccessToken() throws Exception {
        String at = type.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        AdminID admin = mdbc.getAdminID(at);
        if (!admin.getProfile_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            if (dbc.checkNBAdminID(admin.getProfile_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                type.setAdmin_id(admin.getProfile_id());
                type.setType(admin.getType());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public String validateUserType(String adminType) throws Exception {
        String valid = ErrMsg.ERR_ADMIN_TYPE;
        HashSet<String> types = dbc.getSubTypes(type.getType());
        if (types.contains(adminType)) {
            valid = CorrectMsg.CORRECT_ADMIN_TYPE;
        }
        return valid;
    }

    @Override
    public boolean validate(String value, String regX) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() throws SQLException {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

}
