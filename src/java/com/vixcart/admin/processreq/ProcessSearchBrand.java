package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.SearchBrandProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.SearchBrand;
import com.vixcart.admin.resp.mod.Brand;
import com.vixcart.admin.resp.mod.SearchBrandSuccessResponse;
import java.util.ArrayList;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessSearchBrand implements SearchBrandProcessor{
    
    private final SearchBrand req;
    private ArrayList<Brand> res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessSearchBrand(SearchBrand req) throws Exception {
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
    public boolean searchBrand() throws Exception {
        res= dbc.searchBrand(req);
        return true;
    }

    @Override
    public SearchBrandSuccessResponse processRequest() throws Exception {
        SearchBrandSuccessResponse obj = null;
        if (generateToken()) {
            if (searchBrand()) {
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
    public SearchBrandSuccessResponse generateResponse(boolean status) {
        SearchBrandSuccessResponse resp;
        if (status) {
            resp = new SearchBrandSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new SearchBrandSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
