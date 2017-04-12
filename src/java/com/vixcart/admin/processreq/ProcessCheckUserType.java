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
import com.vixcart.admin.req.mod.CheckUserType;
import com.vixcart.admin.resp.mod.CheckUserTypeSuccessResponse;
import java.util.Random;
import com.vixcart.admin.intfc.processreq.CheckUserTypeProcessor;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessCheckUserType implements CheckUserTypeProcessor {
    
    private final CheckUserType checkTyp;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessCheckUserType(CheckUserType checkTyp) throws Exception {
        this.checkTyp = checkTyp;
        this.mdbc = DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = checkTyp.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(checkTyp.getAdmin_id(), accessToken);
    }

    @Override
    public boolean addUpdateTypeActivity() throws Exception {
        return mdbc.updateTypeActivity(checkTyp.getAdmin_id(), checkTyp.getTypeID());
    }

    @Override
    public CheckUserTypeSuccessResponse processRequest() throws Exception {
        CheckUserTypeSuccessResponse obj = null;
        if (generateToken()) {
            if (addUpdateTypeActivity()) {
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
