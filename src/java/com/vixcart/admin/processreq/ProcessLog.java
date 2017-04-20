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
import com.vixcart.admin.intfc.processreq.LogProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.Login;
import com.vixcart.admin.resp.mod.LogSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessLog implements LogProcessor {

    private final Login log;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessLog(Login log) throws Exception {
        this.log = log;
        dbc = DB.getConnection();
        mdbc = DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = log.getuName() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(log.getAdmin_id(), accessToken);
    }

    @Override
    public String generateReport() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogSuccessResponse processRequest() throws Exception {
        LogSuccessResponse obj = null;
        if (generateToken()) {
            if (updateLog()) {
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
    public LogSuccessResponse generateResponse(boolean status) {
        LogSuccessResponse resp;
        if (status) {
            resp = new LogSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new LogSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public boolean updateLog() throws Exception {
        return dbc.updateLog(log.getuName());
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }
}
