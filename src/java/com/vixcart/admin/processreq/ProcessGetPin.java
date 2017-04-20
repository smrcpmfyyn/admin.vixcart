/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.processreq;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetPinProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetPin;
import com.vixcart.admin.resp.mod.GetPinSuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetPin implements GetPinProcessor {
    
    private final GetPin gp;
    private final MongoConnect mdbc;
    private String accessToken;
    private ArrayList<String> allPin;

    public ProcessGetPin(GetPin gp) throws Exception {
        this.gp = gp;
        this.mdbc = DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = gp.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(gp.getAdmin_id(), accessToken);
    }

    @Override
    public void getPin() throws Exception {
        allPin = mdbc.getPin();
    }

    @Override
    public GetPinSuccessResponse processRequest() throws Exception {
        GetPinSuccessResponse resp;
        if (generateToken()) {
            getPin();
            resp = generateResponse(true);
        } else {
            resp = generateResponse(false);
        }
        return resp;
    }

    @Override
    public GetPinSuccessResponse generateResponse(boolean status) {
        GetPinSuccessResponse resp;
        if (status) {
            resp = new GetPinSuccessResponse(ResponseMsg.RESP_OK, accessToken, allPin);
        } else {
            resp = new GetPinSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
    }

}
