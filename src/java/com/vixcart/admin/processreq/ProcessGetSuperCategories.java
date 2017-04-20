package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetSuperCategoriesProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetSuperCategories;
import com.vixcart.admin.resp.mod.SuperCategory;
import com.vixcart.admin.resp.mod.SuperCategorySuccessResponse;
import java.util.ArrayList;
import java.util.Random;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetSuperCategories implements GetSuperCategoriesProcessor {

    private final GetSuperCategories gsc;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;
    private ArrayList<SuperCategory> superCategories;

    public ProcessGetSuperCategories(GetSuperCategories gsc) throws Exception {
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
    public void getAllSuperCategories() throws Exception {
        superCategories = dbc.getAllSuperCategories();
        dbc.closeConnection();
    }

    @Override
    public SuperCategorySuccessResponse processRequest() throws Exception {
        SuperCategorySuccessResponse obj = null;
        if (generateToken()) {
            getAllSuperCategories();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;

    }

    @Override
    public SuperCategorySuccessResponse generateResponse(boolean status) {
        SuperCategorySuccessResponse resp;
        if (status) {
            resp = new SuperCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken, superCategories);
        } else {
            resp = new SuperCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;

    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
