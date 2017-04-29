package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetBPaC2Processor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetBPaCs;
import com.vixcart.admin.resp.mod.BPaC;
import com.vixcart.admin.resp.mod.GetBPaCsSuccessResponse;
import java.util.ArrayList;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetBPaCs implements GetBPaC2Processor{
    
    private final GetBPaCs req;
    private ArrayList<BPaC> res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetBPaCs(GetBPaCs req) throws Exception {
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
    public boolean getBPaCs() throws Exception {
        res= dbc.getBPaCs(req);
        return !res.isEmpty();
    }

    @Override
    public GetBPaCsSuccessResponse processRequest() throws Exception {
        GetBPaCsSuccessResponse obj = null;
        if (generateToken()) {
            if (getBPaCs()) {
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
    public GetBPaCsSuccessResponse generateResponse(boolean status) {
        GetBPaCsSuccessResponse resp;
        if (status) {
            resp = new GetBPaCsSuccessResponse(ResponseMsg.RESP_OK, accessToken,res);
        } else {
            resp = new GetBPaCsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
