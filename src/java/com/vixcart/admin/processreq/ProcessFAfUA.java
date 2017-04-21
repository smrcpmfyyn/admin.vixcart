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
import com.vixcart.admin.intfc.processreq.FAfUAProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.FAfUA;
import com.vixcart.admin.resp.mod.AffiliateActivity;
import com.vixcart.admin.resp.mod.FAfUASuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessFAfUA implements FAfUAProcessor {

    private ArrayList<AffiliateActivity> activities;
    private final FAfUA req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessFAfUA(FAfUA req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
        this.activities = new ArrayList<>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public void getAllFAfUA() throws Exception {
        activities = mdbc.getAllFAfUA(req);
    }

    @Override
    public FAfUASuccessResponse processRequest() throws Exception {
        FAfUASuccessResponse obj = null;
        if (generateToken()) {
            getAllFAfUA();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public FAfUASuccessResponse generateResponse(boolean status) {
        FAfUASuccessResponse resp;
        if (status) {
            resp = new FAfUASuccessResponse(ResponseMsg.RESP_OK, accessToken, activities);
        } else {
            resp = new FAfUASuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }
}


