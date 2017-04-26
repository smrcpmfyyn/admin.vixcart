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
import com.vixcart.admin.intfc.processreq.GetMemberProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetMember;
import com.vixcart.admin.resp.mod.GetMemberSuccessResponse;
import com.vixcart.admin.resp.mod.MemberAllDetails;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetMember implements GetMemberProcessor{
    
    private final GetMember req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private MemberAllDetails md;

    public ProcessGetMember(GetMember req) throws Exception{
        this.req = req;
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
    public boolean getMember() throws Exception {
        md = dbc.getMemberAllDetails(req.getMember_id());
        return !md.getMid().equals("invalid");
    }

    @Override
    public GetMemberSuccessResponse processRequest() throws Exception {
        GetMemberSuccessResponse obj = null;
        if (generateToken()) {
            if (getMember()) {
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
    public GetMemberSuccessResponse generateResponse(boolean status) {
        GetMemberSuccessResponse resp;
        if (status) {
            resp = new GetMemberSuccessResponse(ResponseMsg.RESP_OK, accessToken,md);
        } else {
            resp = new GetMemberSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}

