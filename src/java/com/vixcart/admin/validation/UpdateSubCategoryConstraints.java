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
public class UpdateSubCategoryConstraints implements UpdateSubCategoryValidator {

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
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_DIGIT;
        String name = req.getSubcat();
        if (validate(name, regX)) {
            if (dbc.checkSubCategoryById(name) == 1) {
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
        mdbc.closeConnection();
    }

    @Override
    public String validateOnlineVisibilityStatus() throws Exception {
        String valid = ErrMsg.ERR_ONLINE_VISIBILITY;
        String regX = RegX.REGX_DIGIT;
        String on_status = req.getOn_status();
        if (validate(on_status, regX)) {
            if (on_status.equals("1") || on_status.equals("2")) {
                valid = CorrectMsg.CORRECT_ONLINE_VISIBILITY;
            }
        }
        return valid;
    }

    @Override
    public String validateOfflineVisibilityStatus() throws Exception {
        String valid = ErrMsg.ERR_OFFLINE_VISIBILITY;
        String regX = RegX.REGX_DIGIT;
        String off_status = req.getOff_status();
        if (validate(off_status, regX)) {
            if (off_status.equals("1") || off_status.equals("2")) {
                valid = CorrectMsg.CORRECT_OFFLINE_VISIBILITY;
            }
        }
        return valid;
    }

}
