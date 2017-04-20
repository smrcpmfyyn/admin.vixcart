package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.AddSpecificationProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddSpecification;
import com.vixcart.admin.resp.mod.AddSpecificationSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessAddSpecification implements AddSpecificationProcessor{
    
    private final AddSpecification req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddSpecification(AddSpecification req) throws Exception {
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
    public boolean addSpecification() throws Exception {
        return dbc.addSpecification(req);
    }

    @Override
    public AddSpecificationSuccessResponse processRequest() throws Exception {
        AddSpecificationSuccessResponse obj = null;
        if (generateToken()) {
            if (addSpecification()) {
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
    public AddSpecificationSuccessResponse generateResponse(boolean status) {
        AddSpecificationSuccessResponse resp;
        if (status) {
            resp = new AddSpecificationSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddSpecificationSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
