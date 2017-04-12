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
import com.vixcart.admin.intfc.processreq.UpdateAffiliateProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.UpdateAffiliate;
import com.vixcart.admin.resp.mod.UpdateAffiliateSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessUpdateAffiliate implements UpdateAffiliateProcessor {
    
    private final UpdateAffiliate req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessUpdateAffiliate(UpdateAffiliate req) throws Exception {
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
    public boolean updateAffiliate() throws Exception {
        return dbc.updateAffiliate(req);
    }

    @Override
    public UpdateAffiliateSuccessResponse processRequest() throws Exception {
        UpdateAffiliateSuccessResponse obj = null;
        if (generateToken()) {
            if (updateAffiliate()) {
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
    public UpdateAffiliateSuccessResponse generateResponse(boolean status) {
        UpdateAffiliateSuccessResponse resp;
        if (status) {
            resp = new UpdateAffiliateSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new UpdateAffiliateSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
}
