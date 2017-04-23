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
import com.vixcart.admin.intfc.processreq.ChangeMemberStatusProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.ChangeMemberStatus;
import com.vixcart.admin.resp.mod.ChangeMemberStatusSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessChangeMemberStatus implements ChangeMemberStatusProcessor{
    
    private final ChangeMemberStatus req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessChangeMemberStatus(ChangeMemberStatus gu) throws Exception{
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
    public boolean changeMemberStatus() throws Exception {
        return dbc.changeMemberStatus(req.getMember_id(),req.getStatus());
    }

    @Override
    public ChangeMemberStatusSuccessResponse processRequest() throws Exception {
        ChangeMemberStatusSuccessResponse obj = null;
        if (generateToken()) {
            if (changeMemberStatus()) {
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
    public ChangeMemberStatusSuccessResponse generateResponse(boolean status) {
        ChangeMemberStatusSuccessResponse resp;
        if (status) {
            resp = new ChangeMemberStatusSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new ChangeMemberStatusSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}




