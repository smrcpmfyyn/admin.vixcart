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
import com.vixcart.admin.intfc.processreq.GetAllPremiumPaymentsProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetAllPremiumPayments;
import com.vixcart.admin.resp.mod.AllPremiumPayments;
import com.vixcart.admin.resp.mod.GetAllPremiumPaymentsSuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetAllPremiumPayments implements GetAllPremiumPaymentsProcessor{
    
    private ArrayList<AllPremiumPayments> allPremiumPayments;
    private final GetAllPremiumPayments req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessGetAllPremiumPayments(GetAllPremiumPayments req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
        this.allPremiumPayments = new ArrayList<>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public void getAllPremiumPayments() throws Exception {
        int me = Integer.parseInt(req.getMaxEntries());
        int offset = (Integer.parseInt(req.getPageNo())-1)*me;
        allPremiumPayments = dbc.getAllPremiumPayments(offset,me);
        dbc.closeConnection();
    }

    @Override
    public GetAllPremiumPaymentsSuccessResponse processRequest() throws Exception {
        GetAllPremiumPaymentsSuccessResponse obj = null;
        if (generateToken()) {
            getAllPremiumPayments();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public GetAllPremiumPaymentsSuccessResponse generateResponse(boolean status) {
        GetAllPremiumPaymentsSuccessResponse resp;
        if (status) {
            resp = new GetAllPremiumPaymentsSuccessResponse(ResponseMsg.RESP_OK, accessToken, allPremiumPayments);
        } else {
            resp = new GetAllPremiumPaymentsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
