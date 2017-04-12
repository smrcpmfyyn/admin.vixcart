/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.processreq;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.CheckUserIDProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.CheckUserID;
import com.vixcart.admin.resp.mod.CheckUserIDSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessCheckUserID implements CheckUserIDProcessor{
    
    private final CheckUserID cuid;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessCheckUserID(CheckUserID cuid) throws Exception {
        this.cuid = cuid;
        this.mdbc = DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = cuid.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(cuid.getAdmin_id(), accessToken);
    }

    @Override
    public boolean addUpdateUserIDActivity() throws Exception {
        return mdbc.updateUserIDActivity(cuid.getAdmin_id(), cuid.getUid());
    }

    @Override
    public CheckUserIDSuccessResponse processRequest() throws Exception {
        CheckUserIDSuccessResponse obj = null;
        if (generateToken()) {
            if (addUpdateUserIDActivity()) {
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
    public CheckUserIDSuccessResponse generateResponse(boolean status) {
        CheckUserIDSuccessResponse resp;
        if (status) {
            resp = new CheckUserIDSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new CheckUserIDSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
