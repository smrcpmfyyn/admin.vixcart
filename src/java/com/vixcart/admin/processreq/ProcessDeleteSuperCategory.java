package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.DeleteSuperCategoryProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.DeleteSuperCategory;
import com.vixcart.admin.resp.mod.DeleteSuperCategorySuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessDeleteSuperCategory implements DeleteSuperCategoryProcessor {

    private final DeleteSuperCategory req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessDeleteSuperCategory(DeleteSuperCategory req) throws Exception {
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
    public boolean deleteSuperCategory() throws Exception {
        return dbc.deleteSuperCategory(req);
    }

    @Override
    public DeleteSuperCategorySuccessResponse processRequest() throws Exception {
        DeleteSuperCategorySuccessResponse obj = null;
        if (generateToken()) {
            if (deleteSuperCategory()) {
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
    public DeleteSuperCategorySuccessResponse generateResponse(boolean status) {
        DeleteSuperCategorySuccessResponse resp;
        if (status) {
            resp = new DeleteSuperCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new DeleteSuperCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
