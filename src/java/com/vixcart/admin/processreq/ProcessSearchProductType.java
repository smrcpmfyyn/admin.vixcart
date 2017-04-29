package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.SearchProductTypeProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.SearchProductType;
import com.vixcart.admin.resp.mod.Query;
import com.vixcart.admin.resp.mod.SearchProductTypeSuccessResponse;
import java.util.ArrayList;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessSearchProductType implements SearchProductTypeProcessor{
    
    private final SearchProductType req;
    private  ArrayList<Query> res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessSearchProductType(SearchProductType req) throws Exception {
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
    public boolean searchProductType() throws Exception {
        res = mdbc.searchProductTypes(req.getStr());
        return !res.isEmpty();
    }

    @Override
    public SearchProductTypeSuccessResponse processRequest() throws Exception {
        SearchProductTypeSuccessResponse obj = null;
        if (generateToken()) {
            if (searchProductType()) {
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
    public SearchProductTypeSuccessResponse generateResponse(boolean status) {
        SearchProductTypeSuccessResponse resp;
        if (status) {
            resp = new SearchProductTypeSuccessResponse(ResponseMsg.RESP_OK, accessToken,res);
        } else {
            resp = new SearchProductTypeSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
