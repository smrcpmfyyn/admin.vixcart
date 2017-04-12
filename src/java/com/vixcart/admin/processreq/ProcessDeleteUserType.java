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
import com.vixcart.admin.intfc.processreq.DeleteUserTypeProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.CheckUserType;
import com.vixcart.admin.resp.mod.CheckUserTypeSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessDeleteUserType implements DeleteUserTypeProcessor{
    
    private final CheckUserType checkTyp;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessDeleteUserType(CheckUserType checkTyp) throws Exception {
        this.checkTyp = checkTyp;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = checkTyp.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(checkTyp.getAdmin_id(), accessToken);
    }

    @Override
    public boolean deleteUserType() throws Exception {
        return dbc.deleteUserType(checkTyp.getTypeID());
    }

    @Override
    public CheckUserTypeSuccessResponse processRequest() throws Exception {
        CheckUserTypeSuccessResponse obj = null;
        if (generateToken()) {
            if (deleteUserType()) {
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
    public CheckUserTypeSuccessResponse generateResponse(boolean status) {
        CheckUserTypeSuccessResponse resp;
        if (status) {
            resp = new CheckUserTypeSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new CheckUserTypeSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
