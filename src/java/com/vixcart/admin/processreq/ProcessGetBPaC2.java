package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetBPaC2Processor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetBPaC2;
import com.vixcart.admin.resp.mod.BPaC;
import com.vixcart.admin.resp.mod.GetBPaC2SuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetBPaC2 implements GetBPaC2Processor{
    
    private final GetBPaC2 req;
    private BPaC res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetBPaC2(GetBPaC2 req) throws Exception {
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
    public boolean getBPaC2() throws Exception {
        res= dbc.getBPaC2(req);
        return !res.getBrand().equals("invalid");
    }

    @Override
    public GetBPaC2SuccessResponse processRequest() throws Exception {
        GetBPaC2SuccessResponse obj = null;
        if (generateToken()) {
            if (getBPaC2()) {
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
    public GetBPaC2SuccessResponse generateResponse(boolean status) {
        GetBPaC2SuccessResponse resp;
        if (status) {
            resp = new GetBPaC2SuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new GetBPaC2SuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
