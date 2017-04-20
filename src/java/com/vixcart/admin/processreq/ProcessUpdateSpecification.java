package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.UpdateSpecificationProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.UpdateSpecification;
import com.vixcart.admin.resp.mod.UpdateSpecificationSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessUpdateSpecification implements UpdateSpecificationProcessor{
    
    private final UpdateSpecification req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessUpdateSpecification(UpdateSpecification req) throws Exception {
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
    public boolean updateSpecification() throws Exception {
        return dbc.updateSpecification(req);
    }

    @Override
    public UpdateSpecificationSuccessResponse processRequest() throws Exception {
        UpdateSpecificationSuccessResponse obj = null;
        if (generateToken()) {
            if (updateSpecification()) {
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
    public UpdateSpecificationSuccessResponse generateResponse(boolean status) {
        UpdateSpecificationSuccessResponse resp;
        if (status) {
            resp = new UpdateSpecificationSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new UpdateSpecificationSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
