package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetProductTypeProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetProductType;
import com.vixcart.admin.resp.mod.GetProductTypeSuccessResponse;
import com.vixcart.admin.resp.mod.ProductType;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetProductType implements GetProductTypeProcessor{
    
    private final GetProductType req;
    private ProductType res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetProductType(GetProductType req) throws Exception {
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
    public boolean getProductType() throws Exception {
        res= dbc.getProductType(req);
        return !res.getPtype().equals("invalid");
    }

    @Override
    public GetProductTypeSuccessResponse processRequest() throws Exception {
        GetProductTypeSuccessResponse obj = null;
        if (generateToken()) {
            if (getProductType()) {
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
    public GetProductTypeSuccessResponse generateResponse(boolean status) {
        GetProductTypeSuccessResponse resp;
        if (status) {
            resp = new GetProductTypeSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new GetProductTypeSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
