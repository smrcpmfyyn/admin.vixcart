package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.UpdateSpecificationValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.UpdateSpecification;
import java.sql.SQLException;
import java.util.HashSet;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateSpecificationConstraints implements UpdateSpecificationValidator {

    private final UpdateSpecification req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public UpdateSpecificationConstraints(UpdateSpecification req) throws Exception {
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
    public String validatePType() throws Exception {
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_STRING;
        String category = req.getPType();
        if (validate(category, regX)) {
            if (dbc.checkPType(category) == 0) {
                valid = CorrectMsg.CORRECT_PTYPE;
            } else {
                valid = ErrMsg.ERR_PTYPE_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateSpecific() throws Exception {
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_STRING;
        String category = req.getSpecific();
        if (validate(category, regX)) {
            if (dbc.checkSpecId(category) == 0) {
                valid = CorrectMsg.CORRECT_SPECIFIC;
            } else {
                valid = ErrMsg.ERR_SPECIFIC_EXISTS;
            }
        }
        return valid;
    }
    @Override
    public String validateOn_status() throws Exception {
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_DIGIT;
        String category = req.getOn_status();
        if (validate(category, regX)) {
            if (dbc.checkVisibilityStatusById(category) == 0) {
                valid = CorrectMsg.CORRECT_ONLINE_VISIBILITY;
            } else {
                valid = ErrMsg.ERR_ONLINE_VISIBILITY_NOT_EXISTS;
            }
        }
        return valid;
    }
    @Override
    public String validateOff_status() throws Exception {
        String valid = ErrMsg.ERR_SUB_CATEGORY;
        String regX = RegX.REGX_DIGIT;
        String category = req.getOff_status();
        if (validate(category, regX)) {
            if (dbc.checkVisibilityStatusById(category) == 0) {
                valid = CorrectMsg.CORRECT_OFFLINE_VISIBILITY;
            } else {
                valid = ErrMsg.ERR_OFFLINE_VISIBILITY_NOT_EXISTS;
            }
        }
        return valid;
    }

}
