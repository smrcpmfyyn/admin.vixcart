package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.AddSpecificationValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.AddSpecification;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class AddSpecificationConstraints implements AddSpecificationValidator {

    private final AddSpecification req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public AddSpecificationConstraints(AddSpecification req) throws Exception {
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
        String category = req.getpType();
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
        List<String> specifications = req.getSpecific();
        List<String> rem = new ArrayList<>();
        for (String specific : specifications) {

            if (validate(specific, regX)) {
                if (dbc.checkSpecific(specific, req.getpType()) == 0) {
                } else {
//                    valid = ErrMsg.ERR_SPECIFIC_EXISTS;
                    rem.add(specific);
                }
            } else {
                rem.add(specific);
            }
        }
        specifications.removeAll(rem);
        if (!specifications.isEmpty()) {
//            valid = valid.substring(0, valid.length() - 1);
            valid = CorrectMsg.CORRECT_SPECIFIC;
        } else {
            valid = ErrMsg.ERR_SPECIFIC_EXISTS;
        }

        return valid;
    }
}
