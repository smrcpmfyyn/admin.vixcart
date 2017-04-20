package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.UpdateSubCategoryProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.UpdateSubCategory;
import com.vixcart.admin.resp.mod.UpdateSubCategorySuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessUpdateSubCategory implements UpdateSubCategoryProcessor {

    private final UpdateSubCategory req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessUpdateSubCategory(UpdateSubCategory req) throws Exception {
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
    public boolean updateSubCategory() throws Exception {
        return dbc.updateSubCategory(req);
    }

    @Override
    public UpdateSubCategorySuccessResponse processRequest() throws Exception {
        UpdateSubCategorySuccessResponse obj = null;
        if (generateToken()) {
            if (updateSubCategory()) {
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
    public UpdateSubCategorySuccessResponse generateResponse(boolean status) {
        UpdateSubCategorySuccessResponse resp;
        if (status) {
            resp = new UpdateSubCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new UpdateSubCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }
    
}

