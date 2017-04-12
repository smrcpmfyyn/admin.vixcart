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
import com.vixcart.admin.intfc.processreq.UpdateUserTypeProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.UpdateUserType;
import com.vixcart.admin.resp.mod.UpdateUserTypeSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessUpdateUserType implements UpdateUserTypeProcessor {
    
    private final UpdateUserType updateTyp;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessUpdateUserType(UpdateUserType updateTyp) throws Exception {
        this.updateTyp = updateTyp;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = updateTyp.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(updateTyp.getAdmin_id(), accessToken);
    }

    @Override
    public boolean updateType() throws Exception {
        return dbc.updateUserType(updateTyp);
    }

    @Override
    public UpdateUserTypeSuccessResponse processRequest() throws Exception {
        UpdateUserTypeSuccessResponse obj = null;
        if (generateToken()) {
            if (updateType()) {
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
    public UpdateUserTypeSuccessResponse generateResponse(boolean status) {
        UpdateUserTypeSuccessResponse resp;
        if (status) {
            resp = new UpdateUserTypeSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new UpdateUserTypeSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
}
