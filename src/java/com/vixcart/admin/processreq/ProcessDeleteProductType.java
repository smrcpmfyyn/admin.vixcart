/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.DeleteProductTypeProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.DeleteProductType;
import com.vixcart.admin.resp.mod.DeleteProductTypeSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessDeleteProductType implements DeleteProductTypeProcessor {

    private final DeleteProductType req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessDeleteProductType(DeleteProductType req) throws Exception {
        this.req = req;
        mdbc = DB.getMongoConnection();
        dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean deleteProductType() throws Exception {
        return dbc.deleteProductType(req);
    }

    @Override
    public DeleteProductTypeSuccessResponse processRequest() throws Exception {
        DeleteProductTypeSuccessResponse obj = null;
        if (generateToken()) {
            if (deleteProductType()) {
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
    public DeleteProductTypeSuccessResponse generateResponse(boolean status) {
        DeleteProductTypeSuccessResponse resp;
        if (status) {
            resp = new DeleteProductTypeSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new DeleteProductTypeSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
