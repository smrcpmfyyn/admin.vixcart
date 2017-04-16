package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.AddTaCProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddTaC;
import com.vixcart.admin.resp.mod.AddTaCSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessAddTaC implements AddTaCProcessor{
    
    private final AddTaC req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddTaC(AddTaC req) throws Exception {
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
    public boolean addTaC() throws Exception {
        return dbc.addTaC(req);
    }

    @Override
    public AddTaCSuccessResponse processRequest() throws Exception {
        AddTaCSuccessResponse obj = null;
        if (generateToken()) {
            if (addTaC()) {
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
    public AddTaCSuccessResponse generateResponse(boolean status) {
        AddTaCSuccessResponse resp;
        if (status) {
            resp = new AddTaCSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddTaCSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
