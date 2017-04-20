package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetSubCategoryProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetSubCategory;
import com.vixcart.admin.resp.mod.GetSubCategorySuccessResponse;
import com.vixcart.admin.resp.mod.SubCategory;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetSubCategory implements GetSubCategoryProcessor{
    
    private final GetSubCategory req;
    private SubCategory res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetSubCategory(GetSubCategory req) throws Exception {
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
    public boolean getSubCategory() throws Exception {
        res= dbc.getSubCategory(req);
        return !res.getSub_category().equals("invalid");
    }

    @Override
    public GetSubCategorySuccessResponse processRequest() throws Exception {
        GetSubCategorySuccessResponse obj = null;
        if (generateToken()) {
            if (getSubCategory()) {
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
    public GetSubCategorySuccessResponse generateResponse(boolean status) {
        GetSubCategorySuccessResponse resp;
        if (status) {
            resp = new GetSubCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new GetSubCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
