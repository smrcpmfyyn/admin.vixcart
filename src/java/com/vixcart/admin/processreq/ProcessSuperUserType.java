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
import com.vixcart.admin.req.mod.SuperUserTypes;
import com.vixcart.admin.resp.mod.SuperUserType;
import com.vixcart.admin.resp.mod.SuperTypesSuccessResponse;
import java.util.ArrayList;
import java.util.Random;
import com.vixcart.admin.intfc.processreq.SuperUserTypeProcessor;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessSuperUserType implements SuperUserTypeProcessor{
    
    private ArrayList<SuperUserType> allSType;
    private final SuperUserTypes sType;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessSuperUserType(SuperUserTypes sType) throws Exception {
        this.sType = sType;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    
    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = sType.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(sType.getAdmin_id(), accessToken);
    }

    @Override
    public void getAllSuperTypes() throws Exception {
        allSType = dbc.getAllSuperTypes();
        dbc.closeConnection();
    }

    @Override
    public SuperTypesSuccessResponse processRequest() throws Exception {
        SuperTypesSuccessResponse obj = null;
        if (generateToken()) {
            getAllSuperTypes();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public SuperTypesSuccessResponse generateResponse(boolean status) {
        SuperTypesSuccessResponse resp;
        if (status) {
            resp = new SuperTypesSuccessResponse(ResponseMsg.RESP_OK, accessToken, allSType);
        } else {
            resp = new SuperTypesSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
