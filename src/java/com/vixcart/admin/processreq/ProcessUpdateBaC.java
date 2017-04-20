package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.UpdateBaCProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.UpdateBaC;
import com.vixcart.admin.resp.mod.UpdateBaCSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessUpdateBaC implements UpdateBaCProcessor{
    
    private final UpdateBaC req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessUpdateBaC(UpdateBaC req) throws Exception {
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
    public boolean updateBaC() throws Exception {
        return dbc.updateBaC(req);
    }

    @Override
    public UpdateBaCSuccessResponse processRequest() throws Exception {
        UpdateBaCSuccessResponse obj = null;
        if (generateToken()) {
            if (updateBaC()) {
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
    public UpdateBaCSuccessResponse generateResponse(boolean status) {
        UpdateBaCSuccessResponse resp;
        if (status) {
            resp = new UpdateBaCSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new UpdateBaCSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
