package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetAllProductTypesProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetAllProductTypes;
import com.vixcart.admin.resp.mod.GetAllProductTypesSuccessResponse;
import com.vixcart.admin.resp.mod.ProductType;
import java.util.ArrayList;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetAllProductTypes implements GetAllProductTypesProcessor{
    
    private final GetAllProductTypes req;
    private ArrayList<ProductType> res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetAllProductTypes(GetAllProductTypes req) throws Exception {
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
    public boolean getAllProductTypes() throws Exception {
        res= dbc.getAllProductTypes(req.getPageNo(), req.getMaxEntries());
        return !res.isEmpty();
    }

    @Override
    public GetAllProductTypesSuccessResponse processRequest() throws Exception {
        GetAllProductTypesSuccessResponse obj = null;
        if (generateToken()) {
            if (getAllProductTypes()) {
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
    public GetAllProductTypesSuccessResponse generateResponse(boolean status) {
        GetAllProductTypesSuccessResponse resp;
        if (status) {
            resp = new GetAllProductTypesSuccessResponse(ResponseMsg.RESP_OK, accessToken, res);
        } else {
            resp = new GetAllProductTypesSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
