package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.AddSubCategoryValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.AddSubCategory;
import java.sql.SQLException;
import java.util.HashSet;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class AddSubCategoryConstraints implements AddSubCategoryValidator {

    private final AddSubCategory req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public AddSubCategoryConstraints(AddSubCategory req) throws Exception {
        this.req = req;
        this.dbc = DB.getConnection();
        this.mdbc = DB.getMongoConnection();
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
    public String validateSubCategory() throws Exception {
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_STRING;
        String category = req.getSubCategory();
        if (validate(category, regX)) {
            if (dbc.checkSubCategory(category) == 0) {
                valid = CorrectMsg.CORRECT_SUB_CATEGORY;
            } else {
                valid = ErrMsg.ERR_SUB_CATEGORY_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateCategory() throws Exception {
        String valid = ErrMsg.ERR_CATEGORY;
        String regX = RegX.REGX_DIGIT;
        String category = req.getCategory();
        System.out.println("category = " + category);
        if (validate(category, regX)) {
            if (dbc.checkCategoryById(category) == 1) {
                valid = CorrectMsg.CORRECT_CATEGORY;
            } else {
                valid = ErrMsg.ERR_CATEGORY_NOT_EXISTS;
            }
        }
        return valid;
    }

}
