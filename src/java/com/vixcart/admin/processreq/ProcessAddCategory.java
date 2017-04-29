package com.vixcart.admin.processreq;


// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.AddCategoryProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddCategory;
import com.vixcart.admin.resp.mod.AddCategorySuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessAddCategory implements AddCategoryProcessor{
    
    private final AddCategory req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddCategory(AddCategory req) throws Exception {
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
    public boolean addCategory() throws Exception {
        mdbc.addToSearchIndex(req.getCategory(),"category");
        return dbc.addCategory(req);
    }

    @Override
    public AddCategorySuccessResponse processRequest() throws Exception {
        AddCategorySuccessResponse obj = null;
        if (generateToken()) {
            if (addCategory()) {
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
    public AddCategorySuccessResponse generateResponse(boolean status) {
        AddCategorySuccessResponse resp;
        if (status) {
            resp = new AddCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
