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
import com.vixcart.admin.intfc.processreq.ResetAffiliateUserProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.ResetAffiliateUser;
import com.vixcart.admin.resp.mod.ResetAffiliateUserSuccessResponse;
import java.sql.SQLException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessResetAffiliateUser implements ResetAffiliateUserProcessor {

    private final ResetAffiliateUser req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessResetAffiliateUser(ResetAffiliateUser gu) throws Exception {
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc = DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean resetAffiliateUser() throws Exception {
        boolean flag = false;
        if(dbc.changePassword(req)){
            sendSetPasswordEmail(req.getEmail(), req.getPasswordToken(), req.getName());
            flag = true;
        }
        return flag;
    }

    @Override
    public ResetAffiliateUserSuccessResponse processRequest() throws Exception {
        ResetAffiliateUserSuccessResponse obj = null;
        if (generateToken()) {
            if (resetAffiliateUser()) {
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
    public ResetAffiliateUserSuccessResponse generateResponse(boolean status) {
        ResetAffiliateUserSuccessResponse resp;
        if (status) {
            resp = new ResetAffiliateUserSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new ResetAffiliateUserSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    private void sendSetPasswordEmail(String email, String passwordToken, String name) throws Exception {
        AdminEmail.sendAffiliateUserResetPassword(email, passwordToken, name);
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

}
