package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetCategoriesProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetCategories;
import com.vixcart.admin.resp.mod.Category;
import com.vixcart.admin.resp.mod.GetCategoriesSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetCategories1 implements GetCategoriesProcessor{
    
    private final GetCategories req;
    private Category res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetCategories1(GetCategories req) throws Exception {
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
    public boolean getCategories() throws Exception {
        res= dbc.getCategories(req);
        return !res.getCategory().equals("invalid");
    }

    @Override
    public GetCategoriesSuccessResponse processRequest() throws Exception {
        GetCategoriesSuccessResponse obj = null;
        if (generateToken()) {
            if (getCategories()) {
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
    public GetCategoriesSuccessResponse generateResponse(boolean status) {
        GetCategoriesSuccessResponse resp;
        if (status) {
            resp = new GetCategoriesSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new GetCategoriesSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
