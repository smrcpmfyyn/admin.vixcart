package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.DeleteSpecificationProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.DeleteSpecification;
import com.vixcart.admin.resp.mod.DeleteSpecificationSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessDeleteSpecification implements DeleteSpecificationProcessor{
    
    private final DeleteSpecification req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessDeleteSpecification(DeleteSpecification req) throws Exception {
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
    public boolean deleteSpecification() throws Exception {
        return dbc.deleteSpecification(req);
    }

    @Override
    public DeleteSpecificationSuccessResponse processRequest() throws Exception {
        DeleteSpecificationSuccessResponse obj = null;
        if (generateToken()) {
            if (deleteSpecification()) {
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
    public DeleteSpecificationSuccessResponse generateResponse(boolean status) {
        DeleteSpecificationSuccessResponse resp;
        if (status) {
            resp = new DeleteSpecificationSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new DeleteSpecificationSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
