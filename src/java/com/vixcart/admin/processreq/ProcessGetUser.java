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
import com.vixcart.admin.intfc.processreq.GetUserProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetUser;
import com.vixcart.admin.resp.mod.GetUserSuccessResponse;
import com.vixcart.admin.resp.mod.UserDetails;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetUser implements GetUserProcessor{
    
    private final GetUser req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private UserDetails ud;

    public ProcessGetUser(GetUser gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean getUser() throws Exception {
        ud = dbc.getUserDetails(req.getSlno());
        return !ud.getUname().equals("invalid");
    }

    @Override
    public GetUserSuccessResponse processRequest() throws Exception {
        GetUserSuccessResponse obj = null;
        if (generateToken()) {
            if (getUser()) {
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
    public GetUserSuccessResponse generateResponse(boolean status) {
        GetUserSuccessResponse resp;
        if (status) {
            resp = new GetUserSuccessResponse(ResponseMsg.RESP_OK, accessToken,ud);
        } else {
            resp = new GetUserSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
