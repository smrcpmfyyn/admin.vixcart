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
import com.vixcart.admin.intfc.processreq.GetAffiliateProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetAffiliate;
import com.vixcart.admin.resp.mod.GetAffiliateSuccessResponse;
import com.vixcart.admin.resp.mod.AffiliateDetails;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetAffiliate implements GetAffiliateProcessor{
    
    private final GetAffiliate req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private AffiliateDetails ad;

    public ProcessGetAffiliate(GetAffiliate gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean getAffiliate() throws Exception {
        ad = dbc.getAffiliateDetails(req.getCompany());
        return !ad.getCompany().equals("invalid");
    }

    @Override
    public GetAffiliateSuccessResponse processRequest() throws Exception {
        GetAffiliateSuccessResponse obj = null;
        if (generateToken()) {
            if (getAffiliate()) {
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
    public GetAffiliateSuccessResponse generateResponse(boolean status) {
        GetAffiliateSuccessResponse resp;
        if (status) {
            resp = new GetAffiliateSuccessResponse(ResponseMsg.RESP_OK, accessToken,ad);
        } else {
            resp = new GetAffiliateSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
