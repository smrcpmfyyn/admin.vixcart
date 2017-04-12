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
import com.vixcart.admin.intfc.processreq.GetAffiliateUsersProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetAffiliateUsers;
import com.vixcart.admin.resp.mod.GetAffiliateUsersSuccessResponse;
import com.vixcart.admin.resp.mod.AffiliateUserDetails;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetAffiliateUsers implements GetAffiliateUsersProcessor{
    
    private final GetAffiliateUsers req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private ArrayList<AffiliateUserDetails> aud;

    public ProcessGetAffiliateUsers(GetAffiliateUsers gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
        this.aud = new ArrayList<AffiliateUserDetails>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean getAffiliateUsers() throws Exception {
        dbc.getAffiliateUserDetails(req,aud);
        return aud.size()>0;
    }

    @Override
    public GetAffiliateUsersSuccessResponse processRequest() throws Exception {
        GetAffiliateUsersSuccessResponse obj = null;
        if (generateToken()) {
            if (getAffiliateUsers()) {
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
    public GetAffiliateUsersSuccessResponse generateResponse(boolean status) {
        GetAffiliateUsersSuccessResponse resp;
        if (status) {
            resp = new GetAffiliateUsersSuccessResponse(ResponseMsg.RESP_OK, accessToken,aud);
        } else {
            resp = new GetAffiliateUsersSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
