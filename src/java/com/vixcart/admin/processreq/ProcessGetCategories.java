package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetCategories;
import com.vixcart.admin.resp.mod.Category;
import com.vixcart.admin.resp.mod.CategorySuccessResponse;
import java.util.ArrayList;
import java.util.Random;
import com.vixcart.admin.intfc.processreq.GetAllCategoriesProcessor;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetCategories implements GetAllCategoriesProcessor {

    private final GetCategories gsc;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;
    private ArrayList<Category> allCategories;

    public ProcessGetCategories(GetCategories gsc) throws Exception {
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
    public void getAllCategories() throws Exception {
        allCategories = dbc.getAllCategories();
        dbc.closeConnection();
    }

    @Override
    public CategorySuccessResponse processRequest() throws Exception {
        CategorySuccessResponse obj = null;
        if (generateToken()) {
            getAllCategories();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;

    }

    @Override
    public CategorySuccessResponse generateResponse(boolean status) {
        CategorySuccessResponse resp;
        if (status) {
            resp = new CategorySuccessResponse(ResponseMsg.RESP_OK, accessToken, allCategories);
        } else {
            resp = new CategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;

    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
