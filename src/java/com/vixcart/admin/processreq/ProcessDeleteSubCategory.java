package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.DeleteSubCategoryProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.DeleteSubCategory;
import com.vixcart.admin.resp.mod.DeleteSubCategorySuccessResponse;
import java.util.Random;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessDeleteSubCategory implements DeleteSubCategoryProcessor {

    private final DeleteSubCategory req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessDeleteSubCategory(DeleteSubCategory req) throws Exception {
        this.req = req;
        mdbc = DB.getMongoConnection();
        dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean deleteSubCategory() throws Exception {
        return dbc.deleteSubCategory(req);
    }

    @Override
    public DeleteSubCategorySuccessResponse processRequest() throws Exception {
        DeleteSubCategorySuccessResponse obj = null;
        if (generateToken()) {
            if (deleteSubCategory()) {
                obj = generateResponse(true);
            } else {
                obj = generateResponse(false);
            }
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public DeleteSubCategorySuccessResponse generateResponse(boolean status) {
        DeleteSubCategorySuccessResponse resp;
        if (status) {
            resp = new DeleteSubCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new DeleteSubCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
