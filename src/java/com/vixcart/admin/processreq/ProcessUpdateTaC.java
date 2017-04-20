package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.UpdateTaCProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.UpdateTaC;
import com.vixcart.admin.resp.mod.UpdateTaCSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessUpdateTaC implements UpdateTaCProcessor{
    
    private final UpdateTaC req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessUpdateTaC(UpdateTaC req) throws Exception {
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
    public boolean updateTaC() throws Exception {
        return dbc.updateTaC(req);
    }

    @Override
    public UpdateTaCSuccessResponse processRequest() throws Exception {
        UpdateTaCSuccessResponse obj = null;
        if (generateToken()) {
            if (updateTaC()) {
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
    public UpdateTaCSuccessResponse generateResponse(boolean status) {
        UpdateTaCSuccessResponse resp;
        if (status) {
            resp = new UpdateTaCSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new UpdateTaCSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
