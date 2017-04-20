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
import com.vixcart.admin.intfc.processreq.AddAffiliateUserProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddAffiliateUser;
import com.vixcart.admin.resp.mod.AddAffiliateUserSuccessResponse;
import java.sql.SQLException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessAddAffiliateUser implements AddAffiliateUserProcessor {

    private final AddAffiliateUser req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddAffiliateUser(AddAffiliateUser au) throws Exception {
        this.req = au;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public void addUser() throws Exception {
        try {
            dbc.addAffliateUser(req);
            mdbc.addAffiliateUser(req);
        } catch (Exception e) {
            revokeAddAffiliateUser(req);
            throw e;
        }
    }

    @Override
    public void sendSetPasswordEmail(String email, String passwordToken, String name) throws Exception {
        AdminEmail.sendAffiliateUserResetPassword(email, passwordToken, name);
    }

    @Override
    public AddAffiliateUserSuccessResponse processRequest() throws Exception {
        AddAffiliateUserSuccessResponse obj = null;
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
    public AddAffiliateUserSuccessResponse generateResponse(boolean status) {
        AddAffiliateUserSuccessResponse resp;
        if (status) {
            resp = new AddAffiliateUserSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddAffiliateUserSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    private void revokeAddAffiliateUser(AddAffiliateUser au) throws SQLException {
        dbc.removeAffiliateUser(au.getNew_user_id());
        mdbc.removeAffiliateUser(au.getNew_user_id());
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