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
import com.vixcart.admin.intfc.processreq.GetNoOfWebsitesProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetNoOfWebsites;
import com.vixcart.admin.resp.mod.GetNoOfWebsitesSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetNoOfWebsites implements GetNoOfWebsitesProcessor{
    
    private final GetNoOfWebsites gnow;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;
    private int noOfWebsites;

    public ProcessGetNoOfWebsites(GetNoOfWebsites gnow) throws Exception {
        this.gnow = gnow;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = gnow.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(gnow.getAdmin_id(), accessToken);
    }

    @Override
    public void getNoOfWebsites() throws Exception {
        noOfWebsites = dbc.getNoOfWebsites();
    }

    @Override
    public GetNoOfWebsitesSuccessResponse processRequest() throws Exception {
        GetNoOfWebsitesSuccessResponse resp;
        if (generateToken()) {
            getNoOfWebsites();
            resp = generateResponse(true);
        } else {
            resp = generateResponse(false);
        }
        return resp;
    }

    @Override
    public GetNoOfWebsitesSuccessResponse generateResponse(boolean status) {
        GetNoOfWebsitesSuccessResponse resp;
        if (status) {
            resp = new GetNoOfWebsitesSuccessResponse(ResponseMsg.RESP_OK, accessToken, noOfWebsites);
        } else {
            resp = new GetNoOfWebsitesSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
