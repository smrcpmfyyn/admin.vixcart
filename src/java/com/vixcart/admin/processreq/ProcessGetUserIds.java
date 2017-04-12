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
import com.vixcart.admin.intfc.processreq.GetUserIdsProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetUserIds;
import com.vixcart.admin.resp.mod.GetUserIdsSuccessResponse;
import com.vixcart.admin.resp.mod.UserIds;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetUserIds implements GetUserIdsProcessor{
    
    private final GetUserIds req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private UserIds uids;

    public ProcessGetUserIds(GetUserIds req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc =DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean getUserIds() throws Exception {
        uids = dbc.getUserIds(req.getStr());
        return uids.size()!=0;
    }

    @Override
    public GetUserIdsSuccessResponse processRequest() throws Exception {
        GetUserIdsSuccessResponse obj = null;
        if (generateToken()) {
            if (getUserIds()) {
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
    public GetUserIdsSuccessResponse generateResponse(boolean status) {
        GetUserIdsSuccessResponse resp;
        if (status) {
            resp = new GetUserIdsSuccessResponse(ResponseMsg.RESP_OK, accessToken,uids);
        } else {
            resp = new GetUserIdsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
