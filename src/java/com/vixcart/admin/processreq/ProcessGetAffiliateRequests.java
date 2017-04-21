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
import com.vixcart.admin.intfc.processreq.GetAffiliateRequestsProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetAffiliateRequests;
import com.vixcart.admin.resp.mod.GetAffiliateRequestsSuccessResponse;
import com.vixcart.admin.resp.mod.AffiliateRequests;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetAffiliateRequests implements GetAffiliateRequestsProcessor{
    
    private final GetAffiliateRequests req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private ArrayList<AffiliateRequests> ar;

    public ProcessGetAffiliateRequests(GetAffiliateRequests req) throws Exception{
        this.req = req;
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
    public boolean getAffiliateRequests() throws Exception {
        dbc.getAffiliateRequests(req,ar);
        return !ar.isEmpty();
    }

    @Override
    public GetAffiliateRequestsSuccessResponse processRequest() throws Exception {
        GetAffiliateRequestsSuccessResponse obj = null;
        if (generateToken()) {
            if (getAffiliateRequests()) {
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
    public GetAffiliateRequestsSuccessResponse generateResponse(boolean status) {
        GetAffiliateRequestsSuccessResponse resp;
        if (status) {
            resp = new GetAffiliateRequestsSuccessResponse(ResponseMsg.RESP_OK, accessToken,ar);
        } else {
            resp = new GetAffiliateRequestsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}

