package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.AddProductTypeProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddProductType;
import com.vixcart.admin.resp.mod.AddProductTypeSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessAddProductType implements AddProductTypeProcessor{
    
    private final AddProductType req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddProductType(AddProductType req) throws Exception {
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
    public boolean addProductType() throws Exception {
        return dbc.addProductType(req);
    }

    @Override
    public AddProductTypeSuccessResponse processRequest() throws Exception {
        AddProductTypeSuccessResponse obj = null;
        if (generateToken()) {
            if (addProductType()) {
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
    public AddProductTypeSuccessResponse generateResponse(boolean status) {
        AddProductTypeSuccessResponse resp;
        if (status) {
            resp = new AddProductTypeSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddProductTypeSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
