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
import com.vixcart.admin.req.mod.AddAffiliate;
import com.vixcart.admin.resp.mod.AddAffiliateSuccessResponse;
import java.util.Random;
import com.vixcart.admin.intfc.processreq.AddAffiliateProcessor;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessAddAffiliate implements AddAffiliateProcessor {

    private final AddAffiliate req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddAffiliate(AddAffiliate req) throws Exception {
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
    public boolean addAffiliate() throws Exception {
        mdbc.addAffiliate(req.getCompany());
        return dbc.addAffiliate(req);
    }

    @Override
    public AddAffiliateSuccessResponse processRequest() throws Exception {
        AddAffiliateSuccessResponse obj = null;
        if (generateToken()) {
            if (addAffiliate()) {
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
    public AddAffiliateSuccessResponse generateResponse(boolean status) {
        AddAffiliateSuccessResponse resp;
        if (status) {
            resp = new AddAffiliateSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddAffiliateSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
