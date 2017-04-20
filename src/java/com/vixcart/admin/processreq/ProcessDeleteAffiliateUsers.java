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
import com.vixcart.admin.intfc.processreq.DeleteAffiliateUsersProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.DeleteAffiliateUsers;
import com.vixcart.admin.resp.mod.DeleteAffiliateUsersSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessDeleteAffiliateUsers implements DeleteAffiliateUsersProcessor {

    private final DeleteAffiliateUsers req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessDeleteAffiliateUsers(DeleteAffiliateUsers req) throws Exception {
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
    public boolean blockAffiliateUsers() throws Exception {
        return dbc.blockAffiliateUsers(req.getAuids());
    }

    @Override
    public DeleteAffiliateUsersSuccessResponse processRequest() throws Exception {
        DeleteAffiliateUsersSuccessResponse obj = null;
        if (generateToken()) {
            if (blockAffiliateUsers()) {
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
    public DeleteAffiliateUsersSuccessResponse generateResponse(boolean status) {
        DeleteAffiliateUsersSuccessResponse resp;
        if (status) {
            resp = new DeleteAffiliateUsersSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new DeleteAffiliateUsersSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
