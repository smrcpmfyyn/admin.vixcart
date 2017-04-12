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
import com.vixcart.admin.intfc.processreq.EditUserProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.EditUser;
import com.vixcart.admin.resp.mod.EditUserSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessEditUser implements EditUserProcessor {

    private final EditUser eu;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessEditUser(EditUser eu) throws Exception {
        this.eu = eu;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = eu.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(eu.getAdmin_id(), accessToken);
    }

    @Override
    public void updateUser() throws Exception {
        try {
            dbc.updateUser(eu);
            mdbc.updateUser(eu);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public EditUserSuccessResponse processRequest() throws Exception {
        EditUserSuccessResponse obj = null;
        if (generateToken()) {
            updateUser();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public EditUserSuccessResponse generateResponse(boolean status) {
        EditUserSuccessResponse resp;
        if (status) {
            resp = new EditUserSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new EditUserSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
