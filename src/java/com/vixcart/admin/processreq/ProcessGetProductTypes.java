package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetProductTypesProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetProductTypes;
import com.vixcart.admin.resp.mod.GetProductTypesSuccessResponse;
import com.vixcart.admin.resp.mod.ProductTypes;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetProductTypes implements GetProductTypesProcessor{
    
    private final GetProductTypes req;
    private ProductTypes res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetProductTypes(GetProductTypes req) throws Exception {
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
    public boolean getProductTypes() throws Exception {
        res= dbc.getProductTypes(req);
        return !res.getType().equals("invalid");
    }

    @Override
    public GetProductTypesSuccessResponse processRequest() throws Exception {
        GetProductTypesSuccessResponse obj = null;
        if (generateToken()) {
            if (getProductTypes()) {
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
    public GetProductTypesSuccessResponse generateResponse(boolean status) {
        GetProductTypesSuccessResponse resp;
        if (status) {
            resp = new GetProductTypesSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new GetProductTypesSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
