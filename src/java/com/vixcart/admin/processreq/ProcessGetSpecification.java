package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetSpecificationProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetSpecification;
import com.vixcart.admin.resp.mod.GetSpecificationSuccessResponse;
import com.vixcart.admin.resp.mod.Specifications;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetSpecification implements GetSpecificationProcessor{
    
    private final GetSpecification req;
    private Specifications res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetSpecification(GetSpecification req) throws Exception {
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
    public boolean getSpecification() throws Exception {
        res= dbc.getSpecification(req);
        return !res.getSpecific().equals("invalid");
    }

    @Override
    public GetSpecificationSuccessResponse processRequest() throws Exception {
        GetSpecificationSuccessResponse obj = null;
        if (generateToken()) {
            if (getSpecification()) {
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
    public GetSpecificationSuccessResponse generateResponse(boolean status) {
        GetSpecificationSuccessResponse resp;
        if (status) {
            resp = new GetSpecificationSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new GetSpecificationSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
