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
import com.vixcart.admin.intfc.processreq.GetAffiliatesProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetAffiliates;
import com.vixcart.admin.resp.mod.AffiliateCompany;
import com.vixcart.admin.resp.mod.GetAffiliatesSuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetAffiliates implements GetAffiliatesProcessor{
    
    private ArrayList<AffiliateCompany> affiliates;
    private final GetAffiliates req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessGetAffiliates(GetAffiliates req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
        this.affiliates = new ArrayList<>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public void getAffiliates() throws Exception {
        dbc.getAffiliates(affiliates);
        dbc.closeConnection();
    }

    @Override
    public GetAffiliatesSuccessResponse processRequest() throws Exception {
        GetAffiliatesSuccessResponse obj = null;
        if (generateToken()) {
            getAffiliates();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public GetAffiliatesSuccessResponse generateResponse(boolean status) {
        GetAffiliatesSuccessResponse resp;
        if (status) {
            resp = new GetAffiliatesSuccessResponse(ResponseMsg.RESP_OK, accessToken, affiliates);
        } else {
            resp = new GetAffiliatesSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
