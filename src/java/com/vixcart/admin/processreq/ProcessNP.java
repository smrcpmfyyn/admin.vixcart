/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.processreq;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.processreq.NPProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.mongo.mod.VerifyToken;
import com.vixcart.admin.req.mod.NewPassword;
import com.vixcart.admin.resp.mod.NPSuccessResponse;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessNP implements NPProcessor{
    
    private final NewPassword np;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public ProcessNP(NewPassword np) throws Exception {
        this.np = np;
        dbc = DB.getConnection();
        mdbc = DB.getMongoConnection();
    }

    @Override
    public int changePassword() throws Exception {
        int cp = dbc.changePassword(np);
        dbc.closeConnection();
        return cp;
    }

    @Override
    public long updateTokenStatus() throws Exception {
        long ts = mdbc.updateTokenStatus(np);
        return ts;
    }

    @Override
    public NPSuccessResponse processRequest() throws Exception {
        NPSuccessResponse response = null;
        try{
            np.setAdminId(getAdminId(np.getToken()).getAdmin_id());
            int cp = changePassword();
            if(cp == 1){
                long ts = updateTokenStatus();
                if(ts == 1){
                    response = generateResponse(true);
                }else{
                    response = generateResponse(false);
                }
            }else{
                response = generateResponse(false);
            }
        }catch(Exception ex){
            throw ex;
        }
        return response;
    }

    @Override
    public NPSuccessResponse generateResponse(boolean status) {
        NPSuccessResponse resp;
        if (status) {
            resp = new NPSuccessResponse(ResponseMsg.RESP_OK);
        } else {
            resp = new NPSuccessResponse(ResponseMsg.RESP_NOT_OK);
        }
        return resp;
    }

    @Override
    public VerifyToken getAdminId(String token) throws Exception {
        VerifyToken vt = mdbc.getAdminId(token);
        return vt;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
