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
import com.vixcart.admin.req.mod.SearchAffiliates;
import com.vixcart.admin.resp.mod.Affiliates;
import com.vixcart.admin.resp.mod.SearchAffiliatesSuccessResponse;
import java.util.ArrayList;
import java.util.Random;
import com.vixcart.admin.intfc.processreq.SearchAffiliatesProcessor;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessSearchAffiliates implements SearchAffiliatesProcessor{
    
    private ArrayList<Affiliates> affiliates;
    private final SearchAffiliates req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessSearchAffiliates(SearchAffiliates req) throws Exception {
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
        affiliates = mdbc.searchAffiliates(req.getStr());
    }

    @Override
    public SearchAffiliatesSuccessResponse processRequest() throws Exception {
        SearchAffiliatesSuccessResponse obj = null;
        if (generateToken()) {
            getAffiliates();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public SearchAffiliatesSuccessResponse generateResponse(boolean status) {
        SearchAffiliatesSuccessResponse resp;
        if (status) {
            resp = new SearchAffiliatesSuccessResponse(ResponseMsg.RESP_OK, accessToken, affiliates);
        } else {
            resp = new SearchAffiliatesSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}

