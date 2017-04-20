package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.UpdateSuperCategoryProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.UpdateSuperCategory;
import com.vixcart.admin.resp.mod.UpdateSuperCategorySuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessUpdateSuperCategory implements UpdateSuperCategoryProcessor {

    private final UpdateSuperCategory req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessUpdateSuperCategory(UpdateSuperCategory req) throws Exception {
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
    public boolean updateSuperCategory() throws Exception {
        return dbc.updateSuperCategory(req);
    }

    @Override
    public UpdateSuperCategorySuccessResponse processRequest() throws Exception {
        UpdateSuperCategorySuccessResponse obj = null;
        if (generateToken()) {
            if (updateSuperCategory()) {
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
    public UpdateSuperCategorySuccessResponse generateResponse(boolean status) {
        UpdateSuperCategorySuccessResponse resp;
        if (status) {
            resp = new UpdateSuperCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new UpdateSuperCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
