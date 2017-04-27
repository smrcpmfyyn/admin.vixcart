package com.vixcart.admin.validation;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.GetAllTaCValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.GetAllTaC;
import java.sql.SQLException;
import java.util.HashSet;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetAllTaCConstraints implements GetAllTaCValidator {

    private final GetAllTaC req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public GetAllTaCConstraints(GetAllTaC req) throws Exception {
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
        mdbc.closeConnection();
    }

    @Override
    public String validateOffset() throws Exception {
        String valid = ErrMsg.ERR_OFFSET;
        String regx = RegX.REGX_DIGIT;
        if (validate(req.getPageNo(), regx)) {
            if (validate(req.getMaxEntries(), regx)) {
                int pageNo = Integer.parseInt(req.getPageNo());
                int maxPageNo = dbc.getMaxPageNo("tac_mapping", Integer.parseInt(req.getMaxEntries()));
                if (pageNo <= maxPageNo) {
                    valid = CorrectMsg.CORRECT_OFFSET;
                }else if(maxPageNo == 0){
                    valid = ErrMsg.ERR_OFFSET_EMPTY;
                }
            }
        }
        return valid;  
    }
}
