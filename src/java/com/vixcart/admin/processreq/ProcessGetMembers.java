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
import com.vixcart.admin.intfc.processreq.GetMembersProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetMembers;
import com.vixcart.admin.resp.mod.GetMembersSuccessResponse;
import com.vixcart.admin.resp.mod.MemberDetails;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetMembers implements GetMembersProcessor{
    
    private final GetMembers req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private ArrayList<MemberDetails> md;

    public ProcessGetMembers(GetMembers gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
        this.md = new ArrayList<MemberDetails>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean getMembers() throws Exception {
        dbc.getMemberDetails(req,md);
        return md.size()>0;
    }

    @Override
    public GetMembersSuccessResponse processRequest() throws Exception {
        GetMembersSuccessResponse obj = null;
        if (generateToken()) {
            if (getMembers()) {
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
    public GetMembersSuccessResponse generateResponse(boolean status) {
        GetMembersSuccessResponse resp;
        if (status) {
            resp = new GetMembersSuccessResponse(ResponseMsg.RESP_OK, accessToken,md);
        } else {
            resp = new GetMembersSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}

