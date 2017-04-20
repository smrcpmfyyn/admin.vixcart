package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.DeleteBrandProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.DeleteBrand;
import com.vixcart.admin.resp.mod.DeleteBrandSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessDeleteBrand implements DeleteBrandProcessor{
    
    private final DeleteBrand req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessDeleteBrand(DeleteBrand req) throws Exception {
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
    public boolean deleteBrand() throws Exception {
        return dbc.deleteBrand(req);
    }

    @Override
    public DeleteBrandSuccessResponse processRequest() throws Exception {
        DeleteBrandSuccessResponse obj = null;
        if (generateToken()) {
            if (deleteBrand()) {
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
    public DeleteBrandSuccessResponse generateResponse(boolean status) {
        DeleteBrandSuccessResponse resp;
        if (status) {
            resp = new DeleteBrandSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new DeleteBrandSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
