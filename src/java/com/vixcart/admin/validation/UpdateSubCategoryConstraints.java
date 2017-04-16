package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.UpdateSubCategoryValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.UpdateSubCategory;
import java.sql.SQLException;
import java.util.HashSet;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateSubCategoryConstraints implements UpdateSubCategoryValidator{

    private final UpdateSubCategory req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public UpdateSubCategoryConstraints(UpdateSubCategory req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateUserType(String adminType) throws Exception {
        String valid = ErrMsg.ERR_ADMIN_TYPE;
        HashSet<String> types = dbc.getSubTypes(req.getType());
        if (types.contains(adminType)) {
            valid = CorrectMsg.CORRECT_ADMIN_TYPE;
        }
//        System.out.println("validateUserType");
//        System.out.println("valid = " + valid);
//        System.out.println("types = " + types);
        return valid;
    }

    @Override
    public String validateSubCategory() throws Exception {
        String valid = ErrMsg.ERR_NAME;
        String regX = RegX.REGX_DIGIT;
//        System.out.println("regX = " + regX);
        String name = req.getSubcat();
//        System.out.println("name = " + name);
        if (validate(name, regX)) {
            if (dbc.checkSubCategoryById(name)!=0) {
                valid = CorrectMsg.CORRECT_SUB_CATEGORY;
            } else {
                valid = ErrMsg.ERR_SUB_CATEGORY_NOT_EXISTS;
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
    public String validateOnlineVisibilityStatus() throws Exception {
        String valid = ErrMsg.ERR_NAME;
        String regX = RegX.REGX_DIGIT;
//        System.out.println("regX = " + regX);
        String name = req.getOn_status();
//        System.out.println("name = " + name);
        if (validate(name, regX)) {
            if (dbc.checkVisibilityStatusById(name)!=0) {
                valid = CorrectMsg.CORRECT_ONLINE_VISIBILITY;
            } else {
                valid = ErrMsg.ERR_ONLINE_VISIBILITY_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateOfflineVisibilityStatus() throws Exception {
        String valid = ErrMsg.ERR_NAME;
        String regX = RegX.REGX_DIGIT;
//        System.out.println("regX = " + regX);
        String name = req.getOff_status();
//        System.out.println("name = " + name);
        if (validate(name, regX)) {
            if (dbc.checkVisibilityStatusById(name)!=0) {
                valid = CorrectMsg.CORRECT_OFFLINE_VISIBILITY;
            } else {
                valid = ErrMsg.ERR_OFFLINE_VISIBILITY_NOT_EXISTS;
            }
        }
        return valid;}

}
