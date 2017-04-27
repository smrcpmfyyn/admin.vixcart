package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetSuperCategoryProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetSuperCategory;
import com.vixcart.admin.resp.mod.GetSuperCategorySuccessResponse;
import com.vixcart.admin.resp.mod.SuperCategory;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetSuperCategory implements GetSuperCategoryProcessor{
    
    private final GetSuperCategory req;
    private SuperCategory res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetSuperCategory(GetSuperCategory req) throws Exception {
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
    public boolean getSuperCategory() throws Exception {
        res= dbc.getSuperCategory(req);
        return !res.getSuper_category().equals("invalid");
    }

    @Override
    public GetSuperCategorySuccessResponse processRequest() throws Exception {
        GetSuperCategorySuccessResponse obj = null;
        if (generateToken()) {
            if (getSuperCategory()) {
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
    public GetSuperCategorySuccessResponse generateResponse(boolean status) {
        GetSuperCategorySuccessResponse resp;
        if (status) {
            resp = new GetSuperCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken,res);
        } else {
            resp = new GetSuperCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
