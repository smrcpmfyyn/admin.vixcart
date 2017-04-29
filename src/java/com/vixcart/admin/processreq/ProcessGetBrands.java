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
import com.vixcart.admin.intfc.processreq.GetBrandsProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetBrands;
import com.vixcart.admin.resp.mod.Brand;
import com.vixcart.admin.resp.mod.GetBrandsSuccessResponse;
import java.util.ArrayList;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetBrands implements GetBrandsProcessor{
    
    private final GetBrands req;
    private ArrayList<Brand> res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetBrands(GetBrands req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean getBrands() throws Exception {
        res= dbc.getBrands(req);
        return !res.isEmpty();
    }

    @Override
    public GetBrandsSuccessResponse processRequest() throws Exception {
        GetBrandsSuccessResponse obj = null;
        if (generateToken()) {
            if (getBrands()) {
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
    public GetBrandsSuccessResponse generateResponse(boolean status) {
        GetBrandsSuccessResponse resp;
        if (status) {
            resp = new GetBrandsSuccessResponse(ResponseMsg.RESP_OK, accessToken,res);
        } else {
            resp = new GetBrandsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}