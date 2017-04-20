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
import com.vixcart.admin.req.mod.AddUserType;
import com.vixcart.admin.resp.mod.AddUserTypeSuccessResponse;
import java.util.Random;
import com.vixcart.admin.intfc.processreq.AddUserTypeProcessor;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessAddUserType implements AddUserTypeProcessor{
    
    private final AddUserType addTyp;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddUserType(AddUserType addTyp) throws Exception {
        this.addTyp = addTyp;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = addTyp.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(addTyp.getAdmin_id(), accessToken);
    }

    @Override
    public boolean addType() throws Exception {
        return dbc.addType(addTyp);
    }

    @Override
    public AddUserTypeSuccessResponse processRequest() throws Exception {
        AddUserTypeSuccessResponse obj = null;
        if (generateToken()) {
            if (addType()) {
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
    public AddUserTypeSuccessResponse generateResponse(boolean status) {
        AddUserTypeSuccessResponse resp;
        if (status) {
            resp = new AddUserTypeSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddUserTypeSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
