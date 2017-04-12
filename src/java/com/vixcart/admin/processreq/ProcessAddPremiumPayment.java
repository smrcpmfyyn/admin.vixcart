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
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddPremiumPayment;
import com.vixcart.admin.resp.mod.AddPremiumPaymentSuccessResponse;
import java.util.Random;
import com.vixcart.admin.intfc.processreq.AddPremiumPaymentProcessor;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessAddPremiumPayment implements AddPremiumPaymentProcessor{
    
    private final AddPremiumPayment req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddPremiumPayment(AddPremiumPayment addTyp) throws Exception {
        this.req = addTyp;
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
    public boolean addPremiumPayment() throws Exception {
        return dbc.addPremiumPayment(req);
    }

    @Override
    public AddPremiumPaymentSuccessResponse processRequest() throws Exception {
        AddPremiumPaymentSuccessResponse obj = null;
        if (generateToken()) {
            if (addPremiumPayment()) {
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
    public AddPremiumPaymentSuccessResponse generateResponse(boolean status) {
        AddPremiumPaymentSuccessResponse resp;
        if (status) {
            resp = new AddPremiumPaymentSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddPremiumPaymentSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}

