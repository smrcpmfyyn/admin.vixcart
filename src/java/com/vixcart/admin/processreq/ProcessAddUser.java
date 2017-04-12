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
import com.vixcart.admin.intfc.processreq.AddUserProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddUser;
import com.vixcart.admin.resp.mod.AddUserSuccessResponse;
import java.sql.SQLException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessAddUser implements AddUserProcessor {

    private final AddUser au;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddUser(AddUser au) throws Exception {
        this.au = au;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public void addUser() throws Exception {
        try {
            dbc.addUser(au);
            mdbc.addUser(au);
        } catch (Exception e) {
            revokeAddUser(au);
            throw e;
        }
    }

    @Override
    public void sendSetPasswordEmail(String email, String passwordToken, String name) throws Exception {
        AdminEmail.sendResetPassword(email, passwordToken, name);
    }

    @Override
    public AddUserSuccessResponse processRequest() throws Exception {
        AddUserSuccessResponse obj = null;
        if (generateToken()) {
            addUser();
            sendSetPasswordEmail(au.getEmail(), au.getPasswordToken(), au.getName());
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public AddUserSuccessResponse generateResponse(boolean status) {
        AddUserSuccessResponse resp;
        if (status) {
            resp = new AddUserSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddUserSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    private void revokeAddUser(AddUser au) throws SQLException {
        dbc.removeUser(au.getNew_admin_id());
        mdbc.removeUser(au.getNew_admin_id());
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = au.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(au.getAdmin_id(), accessToken);
    }

}
