package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.AddBaCProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddBPaC;
import com.vixcart.admin.resp.mod.AddBPaCSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessAddBPaC implements AddBaCProcessor{
    
    private final AddBPaC req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddBPaC(AddBPaC req) throws Exception {
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
    public boolean addBPaC() throws Exception {
        return dbc.addBPaC(req);
    }

    @Override
    public AddBPaCSuccessResponse processRequest() throws Exception {
        AddBPaCSuccessResponse obj = null;
        if (generateToken()) {
            if (addBPaC()) {
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
    public AddBPaCSuccessResponse generateResponse(boolean status) {
        AddBPaCSuccessResponse resp;
        if (status) {
            resp = new AddBPaCSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddBPaCSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
