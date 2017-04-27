package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.UpdateBaCValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.UpdateBPaC;
import java.sql.SQLException;
import java.util.HashSet;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateBaCConstraints implements UpdateBaCValidator {

    private final UpdateBPaC req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public UpdateBaCConstraints(UpdateBPaC req) throws Exception {
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
    @Override
    public String validateBrand() throws Exception {
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_STRING;
        String brand = req.getBrand();
        if (validate(brand, regX)) {
            if (dbc.checkBrand(brand) == 0) {
                valid = CorrectMsg.CORRECT_BRAND;
            } else {
                valid = ErrMsg.ERR_BRAND_EXISTS;
            }
        }
        return valid;
    }
    @Override
    public String validateCateg() throws Exception {
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_STRING;
        String categ = req.getCateg();
        if (validate(categ, regX)) {
            if (dbc.checkCategory(categ) == 0) {
                valid = CorrectMsg.CORRECT_CATEGORY;
            } else {
                valid = ErrMsg.ERR_CATEGORY_EXISTS;
            }
        }
        return valid;
    }
    @Override
    public String validatePtype() throws Exception {
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_STRING;
        String ptype = req.getPtype();
        if (validate(ptype, regX)) {
            if (dbc.checkPType(ptype) == 0) {
                valid = CorrectMsg.CORRECT_PTYPE;
            } else {
                valid = ErrMsg.ERR_PTYPE_EXISTS;
            }
        }
        return valid;
    }
    @Override
    public String validateOn_status() throws Exception {
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_DIGIT;
        String on_status = req.getOn_status();
        if (validate(on_status, regX)) {
            if (dbc.checkVisibilityStatusById(on_status) == 0) {
                valid = CorrectMsg.CORRECT_ONLINE_VISIBILITY;
            } else {
                valid = ErrMsg.ERR_ONLINE_VISIBILITY;
            }
        }
        return valid;
    }
    @Override
    public String validateOff_status() throws Exception {
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_DIGIT;
        String off_status = req.getOff_status();
        if (validate(off_status, regX)) {
            if (dbc.checkVisibilityStatusById(off_status) == 0) {
                valid = CorrectMsg.CORRECT_OFFLINE_VISIBILITY;
            } else {
                valid = ErrMsg.ERR_OFFLINE_VISIBILITY;
            }
        }
        return valid;
    }
}