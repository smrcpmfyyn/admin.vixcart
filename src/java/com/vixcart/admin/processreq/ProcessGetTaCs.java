/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetTaCsProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetTaCs;
import com.vixcart.admin.resp.mod.GetTaCsSuccessResponse;
import com.vixcart.admin.resp.mod.TaC;
import java.util.ArrayList;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetTaCs implements GetTaCsProcessor{
    
    private final GetTaCs req;
    private ArrayList<TaC> res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetTaCs(GetTaCs req) throws Exception {
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
    public boolean getTaCs() throws Exception {
        res= dbc.getTaCs(req);
        return !res.isEmpty();
    }

    @Override
    public GetTaCsSuccessResponse processRequest() throws Exception {
        GetTaCsSuccessResponse obj = null;
        if (generateToken()) {
            if (getTaCs()) {
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
    public GetTaCsSuccessResponse generateResponse(boolean status) {
        GetTaCsSuccessResponse resp;
        if (status) {
            resp = new GetTaCsSuccessResponse(ResponseMsg.RESP_OK, accessToken,res);
        } else {
            resp = new GetTaCsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
