/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.processreq;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetAffiliateRequestProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetAffiliateRequest;
import com.vixcart.admin.resp.mod.GetAffiliateRequestSuccessResponse;
import com.vixcart.admin.resp.mod.AffiliateRequest;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetAffiliateRequest implements GetAffiliateRequestProcessor{
    
    private final GetAffiliateRequest req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private AffiliateRequest ar;

    public ProcessGetAffiliateRequest(GetAffiliateRequest gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean getAffiliateRequest() throws Exception {
        ar = dbc.getAffiliateRequest(req.getReqId());
        return !ar.getRid().equals("invalid");
    }

    @Override
    public GetAffiliateRequestSuccessResponse processRequest() throws Exception {
        GetAffiliateRequestSuccessResponse obj = null;
        if (generateToken()) {
            if (getAffiliateRequest()) {
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
    public GetAffiliateRequestSuccessResponse generateResponse(boolean status) {
        GetAffiliateRequestSuccessResponse resp;
        if (status) {
            resp = new GetAffiliateRequestSuccessResponse(ResponseMsg.RESP_OK, accessToken,ar);
        } else {
            resp = new GetAffiliateRequestSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}

