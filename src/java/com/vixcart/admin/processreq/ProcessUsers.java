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
import com.vixcart.admin.intfc.processreq.UsersProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.Users;
import com.vixcart.admin.resp.mod.User;
import com.vixcart.admin.resp.mod.UsersSuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessUsers implements UsersProcessor {

    private ArrayList<User> allUser;
    private final Users user;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessUsers(Users user) throws Exception {
        this.user = user;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
        this.allUser = new ArrayList<>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = user.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(user.getAdmin_id(), accessToken);
    }

    @Override
    public void getAllUsers() throws Exception {
        allUser = dbc.getAllUsers();
        dbc.closeConnection();
    }

    @Override
    public UsersSuccessResponse processRequest() throws Exception {
        UsersSuccessResponse obj = null;
        if (generateToken()) {
            getAllUsers();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public UsersSuccessResponse generateResponse(boolean status) {
        UsersSuccessResponse resp;
        if (status) {
            resp = new UsersSuccessResponse(ResponseMsg.RESP_OK, accessToken, allUser);
        } else {
            resp = new UsersSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
