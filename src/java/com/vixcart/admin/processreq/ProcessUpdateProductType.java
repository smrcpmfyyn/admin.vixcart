package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.UpdateProductTypeProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.UpdateProductType;
import com.vixcart.admin.resp.mod.UpdateProductTypeSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessUpdateProductType implements UpdateProductTypeProcessor{
    
    private final UpdateProductType req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessUpdateProductType(UpdateProductType req) throws Exception {
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
    public boolean updateProductType() throws Exception {
        return dbc.updateProductType(req);
    }

    @Override
    public UpdateProductTypeSuccessResponse processRequest() throws Exception {
        UpdateProductTypeSuccessResponse obj = null;
        if (generateToken()) {
            if (updateProductType()) {
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
    public UpdateProductTypeSuccessResponse generateResponse(boolean status) {
        UpdateProductTypeSuccessResponse resp;
        if (status) {
            resp = new UpdateProductTypeSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new UpdateProductTypeSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
