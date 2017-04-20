package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetBPaCProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetBPaC;
import com.vixcart.admin.resp.mod.BPaC;
import com.vixcart.admin.resp.mod.GetBPaCSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetBPaC implements GetBPaCProcessor{
    
    private final GetBPaC req;
    private BPaC res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetBPaC(GetBPaC req) throws Exception {
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
    public boolean getBPaC() throws Exception {
        res= dbc.getBPaC(req);
        return !res.getBrand().equals("invalid");
    }

    @Override
    public GetBPaCSuccessResponse processRequest() throws Exception {
        GetBPaCSuccessResponse obj = null;
        if (generateToken()) {
            if (getBPaC()) {
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
    public GetBPaCSuccessResponse generateResponse(boolean status) {
        GetBPaCSuccessResponse resp;
        if (status) {
            resp = new GetBPaCSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new GetBPaCSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
