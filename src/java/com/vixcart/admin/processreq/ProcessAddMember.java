/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.processreq;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.email.AdminEmail;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.AddMemberProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddMember;
import com.vixcart.admin.resp.mod.AddMemberSuccessResponse;
import java.sql.SQLException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessAddMember implements AddMemberProcessor {

    private final AddMember req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddMember(AddMember au) throws Exception {
        this.req = au;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public void addUser() throws Exception {
        try {
            dbc.addMember(req);
            mdbc.addMember(req);
        } catch (Exception e) {
            revokeAddMember(req);
            throw e;
        }
    }

    @Override
    public void sendSetPasswordEmail(String email, String passwordToken, String name) throws Exception {
        AdminEmail.sendMemberResetPassword(email, passwordToken, name);
    }

    @Override
    public AddMemberSuccessResponse processRequest() throws Exception {
        AddMemberSuccessResponse obj = null;
        if (generateToken()) {
            addUser();
            sendSetPasswordEmail(req.getEmail(), req.getPasswordToken(), req.getName());
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public AddMemberSuccessResponse generateResponse(boolean status) {
        AddMemberSuccessResponse resp;
        if (status) {
            resp = new AddMemberSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddMemberSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    private void revokeAddMember(AddMember au) throws SQLException {
        dbc.removeMember(au.getNew_member_id());
        mdbc.removeMember(au.getNew_member_id());
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
