package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetCategoryProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetCategory;
import com.vixcart.admin.resp.mod.Category;
import com.vixcart.admin.resp.mod.GetCategorySuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetCategory implements GetCategoryProcessor{
    
    private final GetCategory req;
    private  Category res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetCategory(GetCategory req) throws Exception {
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
    public boolean getCategory() throws Exception {
        res= dbc.getCategory(req);
        return !res.getCategory().equals("invalid");
    }

    @Override
    public GetCategorySuccessResponse processRequest() throws Exception {
        GetCategorySuccessResponse obj = null;
        if (generateToken()) {
            if (getCategory()) {
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
    public GetCategorySuccessResponse generateResponse(boolean status) {
        GetCategorySuccessResponse resp;
        if (status) {
            resp = new GetCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken,res);
        } else {
            resp = new GetCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
