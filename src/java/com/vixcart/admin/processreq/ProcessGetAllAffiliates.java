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
import com.vixcart.admin.intfc.processreq.GetAllAffiliatesProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetAllAffiliates;
import com.vixcart.admin.resp.mod.AllAffiliates;
import com.vixcart.admin.resp.mod.GetAllAffiliatesSuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetAllAffiliates implements GetAllAffiliatesProcessor{
    
    private ArrayList<AllAffiliates> allAffiliates;
    private final GetAllAffiliates req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessGetAllAffiliates(GetAllAffiliates req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
        this.allAffiliates = new ArrayList<>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public void getAllAffiliates() throws Exception {
        int me = Integer.parseInt(req.getMaxEntries());
        int offset = (Integer.parseInt(req.getPageNo())-1)*me;
        allAffiliates = dbc.getAllAffiliates(offset,me);
        dbc.closeConnection();
    }

    @Override
    public GetAllAffiliatesSuccessResponse processRequest() throws Exception {
        GetAllAffiliatesSuccessResponse obj = null;
        if (generateToken()) {
            getAllAffiliates();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public GetAllAffiliatesSuccessResponse generateResponse(boolean status) {
        GetAllAffiliatesSuccessResponse resp;
        if (status) {
            resp = new GetAllAffiliatesSuccessResponse(ResponseMsg.RESP_OK, accessToken, allAffiliates);
        } else {
            resp = new GetAllAffiliatesSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
