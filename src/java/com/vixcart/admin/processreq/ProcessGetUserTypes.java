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
import com.vixcart.admin.intfc.processreq.GetUserTypeProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.UserTypes;
import com.vixcart.admin.resp.mod.GetUserTypeSuccessResponse;
import com.vixcart.admin.resp.mod.UserType1;
import com.vixcart.admin.resp.mod.UserTypesSuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetUserTypes implements GetUserTypeProcessor{
    
    private ArrayList<UserType1> allType;
    private final UserTypes type;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessGetUserTypes(UserTypes type) throws Exception {
        this.type = type;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = type.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(type.getAdmin_id(), accessToken);
    }

    @Override
    public void getAllTypes() throws Exception {
        allType = dbc.getAllUserTypes();
        dbc.closeConnection();
    }

    @Override
    public GetUserTypeSuccessResponse processRequest() throws Exception {
        GetUserTypeSuccessResponse obj = null;
        if (generateToken()) {
            getAllTypes();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public GetUserTypeSuccessResponse generateResponse(boolean status) {
        GetUserTypeSuccessResponse resp;
        if (status) {
            resp = new GetUserTypeSuccessResponse(ResponseMsg.RESP_OK, accessToken, allType);
        } else {
            resp = new GetUserTypeSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
