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
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.SearchMemberIDs;
import com.vixcart.admin.resp.mod.MemberID;
import com.vixcart.admin.resp.mod.SearchMemberIDsSuccessResponse;
import java.util.ArrayList;
import java.util.Random;
import com.vixcart.admin.intfc.processreq.SearchMemberIDsProcessor;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessSearchMemberIDs implements SearchMemberIDsProcessor{
    
    private ArrayList<MemberID> memberIDs;
    private final SearchMemberIDs req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessSearchMemberIDs(SearchMemberIDs req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
        this.memberIDs = new ArrayList<>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public void getMemberIDs() throws Exception {
        memberIDs = mdbc.searchMemberIDs(req.getStr());
    }

    @Override
    public SearchMemberIDsSuccessResponse processRequest() throws Exception {
        SearchMemberIDsSuccessResponse obj = null;
        if (generateToken()) {
            getMemberIDs();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public SearchMemberIDsSuccessResponse generateResponse(boolean status) {
        SearchMemberIDsSuccessResponse resp;
        if (status) {
            resp = new SearchMemberIDsSuccessResponse(ResponseMsg.RESP_OK, accessToken, memberIDs);
        } else {
            resp = new SearchMemberIDsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
