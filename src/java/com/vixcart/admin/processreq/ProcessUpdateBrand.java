package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.UpdateBrandProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.UpdateBrand;
import com.vixcart.admin.resp.mod.UpdateBrandSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessUpdateBrand implements UpdateBrandProcessor{
    
    private final UpdateBrand req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessUpdateBrand(UpdateBrand req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean updateBrand() throws Exception {
        return dbc.updateBrand(req);
    }

    @Override
    public UpdateBrandSuccessResponse processRequest() throws Exception {
        UpdateBrandSuccessResponse obj = null;
        if (generateToken()) {
            if (updateBrand()) {
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
    public UpdateBrandSuccessResponse generateResponse(boolean status) {
        UpdateBrandSuccessResponse resp;
        if (status) {
            resp = new UpdateBrandSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new UpdateBrandSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
