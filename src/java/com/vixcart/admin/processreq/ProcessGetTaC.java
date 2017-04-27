package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetTaCProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetTaC;
import com.vixcart.admin.resp.mod.GetTaCSuccessResponse;
import com.vixcart.admin.resp.mod.TaC;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetTaC implements GetTaCProcessor{
    
    private final GetTaC req;
    private  TaC res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetTaC(GetTaC req) throws Exception {
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
    public boolean getTaC() throws Exception {
        res= dbc.getTaC(req);
        return !res.getProduct_type().equals("invalid");
    }

    @Override
    public GetTaCSuccessResponse processRequest() throws Exception {
        GetTaCSuccessResponse obj = null;
        if (generateToken()) {
            if (getTaC()) {
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
    public GetTaCSuccessResponse generateResponse(boolean status) {
        GetTaCSuccessResponse resp;
        if (status) {
            resp = new GetTaCSuccessResponse(ResponseMsg.RESP_OK, accessToken,res);
        } else {
            resp = new GetTaCSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
