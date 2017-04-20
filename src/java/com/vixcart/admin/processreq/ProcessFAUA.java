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
import com.vixcart.admin.intfc.processreq.FAUAProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.FAUA;
import com.vixcart.admin.resp.mod.Activity;
import com.vixcart.admin.resp.mod.FAUASuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessFAUA implements FAUAProcessor {

    private ArrayList<Activity> activities;
    private final FAUA req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessFAUA(FAUA req) throws Exception {
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
    public void getAllFAUA() throws Exception {
        activities = mdbc.getAllFAUA(req);
    }

    @Override
    public FAUASuccessResponse processRequest() throws Exception {
        FAUASuccessResponse obj = null;
        if (generateToken()) {
            getAllFAUA();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public FAUASuccessResponse generateResponse(boolean status) {
        FAUASuccessResponse resp;
        if (status) {
            resp = new FAUASuccessResponse(ResponseMsg.RESP_OK, accessToken, activities);
        } else {
            resp = new FAUASuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
