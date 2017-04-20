package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetBrandProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetBrand;
import com.vixcart.admin.resp.mod.Brand;
import com.vixcart.admin.resp.mod.GetBrandSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetBrand implements GetBrandProcessor{
    
    private final GetBrand req;
    private Brand res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetBrand(GetBrand req) throws Exception {
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
    public boolean getBrand() throws Exception {
        res= dbc.getBrand(req);
        return !res.getBrand().equals("invalid");
    }

    @Override
    public GetBrandSuccessResponse processRequest() throws Exception {
        GetBrandSuccessResponse obj = null;
        if (generateToken()) {
            if (getBrand()) {
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
    public GetBrandSuccessResponse generateResponse(boolean status) {
        GetBrandSuccessResponse resp;
        if (status) {
            resp = new GetBrandSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new GetBrandSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
