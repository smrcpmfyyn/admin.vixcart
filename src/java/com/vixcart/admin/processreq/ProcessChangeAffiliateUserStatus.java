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
import com.vixcart.admin.intfc.processreq.ChangeAffiliateUserStatusProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.ChangeAffiliateUserStatus;
import com.vixcart.admin.resp.mod.ChangeAffiliateUserStatusSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessChangeAffiliateUserStatus implements ChangeAffiliateUserStatusProcessor{
    
    private final ChangeAffiliateUserStatus req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessChangeAffiliateUserStatus(ChangeAffiliateUserStatus gu) throws Exception{
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
    public boolean changeAffiliateUserStatus() throws Exception {
        return dbc.changeAffiliateUserStatus(req.getUser_id(),req.getStatus());
    }

    @Override
    public ChangeAffiliateUserStatusSuccessResponse processRequest() throws Exception {
        ChangeAffiliateUserStatusSuccessResponse obj = null;
        if (generateToken()) {
            if (changeAffiliateUserStatus()) {
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
    public ChangeAffiliateUserStatusSuccessResponse generateResponse(boolean status) {
        ChangeAffiliateUserStatusSuccessResponse resp;
        if (status) {
            resp = new ChangeAffiliateUserStatusSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new ChangeAffiliateUserStatusSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}


