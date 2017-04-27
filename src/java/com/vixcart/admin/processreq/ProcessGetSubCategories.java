package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetSubCategoriesProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetSubCategories;
import com.vixcart.admin.resp.mod.SubCategory;
import com.vixcart.admin.resp.mod.SubCategorySuccessResponse;
import java.util.ArrayList;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetSubCategories implements GetSubCategoriesProcessor {

    private final GetSubCategories gsc;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;
    private ArrayList<SubCategory> allSubCategories;

    public ProcessGetSubCategories(GetSubCategories gsc) throws Exception {
        this.gsc = gsc;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = gsc.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(gsc.getAdmin_id(), accessToken);

    }

    @Override
    public void getAllSubCategories() throws Exception {
        allSubCategories = dbc.getAllSubCategories();
    }

    @Override
    public SubCategorySuccessResponse processRequest() throws Exception {
        SubCategorySuccessResponse obj = null;
        if (generateToken()) {
            getAllSubCategories();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;

    }

    @Override
    public SubCategorySuccessResponse generateResponse(boolean status) {
        SubCategorySuccessResponse resp;
        if (status) {
            resp = new SubCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken, allSubCategories);
        } else {
            resp = new SubCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;

    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
