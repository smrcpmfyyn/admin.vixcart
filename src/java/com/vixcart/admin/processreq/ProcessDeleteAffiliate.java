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
import com.vixcart.admin.intfc.processreq.DeleteAffiliateProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.DeleteAffiliate;
import com.vixcart.admin.resp.mod.DeleteAffiliateSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessDeleteAffiliate implements DeleteAffiliateProcessor {

    private final DeleteAffiliate req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessDeleteAffiliate(DeleteAffiliate req) throws Exception {
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
    public boolean blockAffiliate() throws Exception {
        return dbc.blockAffiliate(req.getAffiliate());
    }

    @Override
    public DeleteAffiliateSuccessResponse processRequest() throws Exception {
        DeleteAffiliateSuccessResponse obj = null;
        if (generateToken()) {
            if (blockAffiliate()) {
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
    public DeleteAffiliateSuccessResponse generateResponse(boolean status) {
        DeleteAffiliateSuccessResponse resp;
        if (status) {
            resp = new DeleteAffiliateSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new DeleteAffiliateSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
