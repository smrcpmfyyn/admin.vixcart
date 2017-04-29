package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.LoadSpecificationsProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.LoadSpecifications;
import com.vixcart.admin.resp.mod.LoadSpecificationsSuccessResponse;
import com.vixcart.admin.resp.mod.Specifications;
import java.util.ArrayList;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessLoadSpecifications implements LoadSpecificationsProcessor{
    
    private final LoadSpecifications req;
    private ArrayList<Specifications> res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessLoadSpecifications(LoadSpecifications req) throws Exception {
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
    public boolean loadSpecifications() throws Exception {
        res= dbc.loadSpecifications(req);
        return !res.isEmpty();
    }

    @Override
    public LoadSpecificationsSuccessResponse processRequest() throws Exception {
        LoadSpecificationsSuccessResponse obj = null;
        if (generateToken()) {
            if (loadSpecifications()) {
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
    public LoadSpecificationsSuccessResponse generateResponse(boolean status) {
        LoadSpecificationsSuccessResponse resp;
        if (status) {
            resp = new LoadSpecificationsSuccessResponse(ResponseMsg.RESP_OK, accessToken,res);
        } else {
            resp = new LoadSpecificationsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
