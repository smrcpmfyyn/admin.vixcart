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
import com.vixcart.admin.intfc.processreq.GetActivePremiumPaymentsProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetActivePremiumPayments;
import com.vixcart.admin.resp.mod.PremiumPayments;
import com.vixcart.admin.resp.mod.GetActivePremiumPaymentsSuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetActivePremiumPayments implements GetActivePremiumPaymentsProcessor{
    
    private ArrayList<PremiumPayments> activePremiumPayments;
    private final GetActivePremiumPayments req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessGetActivePremiumPayments(GetActivePremiumPayments req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
        this.activePremiumPayments = new ArrayList<>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public void getActivePremiumPayments() throws Exception {
        int me = Integer.parseInt(req.getMaxEntries());
        int offset = (Integer.parseInt(req.getPageNo())-1)*me;
        activePremiumPayments = dbc.getActivePremiumPayments(offset,me,req.getCompany());
        dbc.closeConnection();
    }

    @Override
    public GetActivePremiumPaymentsSuccessResponse processRequest() throws Exception {
        GetActivePremiumPaymentsSuccessResponse obj = null;
        if (generateToken()) {
            getActivePremiumPayments();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public GetActivePremiumPaymentsSuccessResponse generateResponse(boolean status) {
        GetActivePremiumPaymentsSuccessResponse resp;
        if (status) {
            resp = new GetActivePremiumPaymentsSuccessResponse(ResponseMsg.RESP_OK, accessToken, activePremiumPayments);
        } else {
            resp = new GetActivePremiumPaymentsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
