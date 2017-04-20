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
import com.vixcart.admin.intfc.processreq.GetNoOfMembersProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetNoOfMembers;
import com.vixcart.admin.resp.mod.GetNoOfMembersSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetNoOfMembers implements GetNoOfMembersProcessor{
    
    private final GetNoOfMembers req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;
    private int noOfMembers;

    public ProcessGetNoOfMembers(GetNoOfMembers req) throws Exception {
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
    public void getNoOfMembers() throws Exception {
        noOfMembers = dbc.getNoOfMembers();
    }

    @Override
    public GetNoOfMembersSuccessResponse processRequest() throws Exception {
        GetNoOfMembersSuccessResponse resp;
        if (generateToken()) {
            getNoOfMembers();
            resp = generateResponse(true);
        } else {
            resp = generateResponse(false);
        }
        return resp;
    }

    @Override
    public GetNoOfMembersSuccessResponse generateResponse(boolean status) {
         GetNoOfMembersSuccessResponse resp;
        if (status) {
            resp = new GetNoOfMembersSuccessResponse(ResponseMsg.RESP_OK, accessToken, noOfMembers);
        } else {
            resp = new GetNoOfMembersSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
