package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.AddSubCategoryProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddSubCategory;
import com.vixcart.admin.resp.mod.AddSubCategorySuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessAddSubCategory implements AddSubCategoryProcessor{
    
    private final AddSubCategory req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddSubCategory(AddSubCategory req) throws Exception {
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
    public boolean addSubCategory() throws Exception {
        return dbc.addSubCategory(req);
    }

    @Override
    public AddSubCategorySuccessResponse processRequest() throws Exception {
        AddSubCategorySuccessResponse obj = null;
        if (generateToken()) {
            if (addSubCategory()) {
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
    public AddSubCategorySuccessResponse generateResponse(boolean status) {
        AddSubCategorySuccessResponse resp;
        if (status) {
            resp = new AddSubCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddSubCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
