
package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.AddSuperCategoryProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.AddSuperCategory;
import com.vixcart.admin.resp.mod.AddSuperCategorySuccessResponse;
import java.util.Random;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessAddSuperCategory implements AddSuperCategoryProcessor {

    private final AddSuperCategory req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessAddSuperCategory(AddSuperCategory req) throws Exception {
        this.req = req;
        mdbc = DB.getMongoConnection();
        dbc = DB.getConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAdmin_id(), accessToken);
    }

    @Override
    public boolean addSuperCategory() throws Exception {
        mdbc.addToSearchIndex(req.getSuper_category(),"super_category");
        return dbc.addSuperCategory(req);
    }

    @Override
    public AddSuperCategorySuccessResponse processRequest() throws Exception {
        AddSuperCategorySuccessResponse obj = null;
        if (generateToken()) {
            if (addSuperCategory()) {
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
    public AddSuperCategorySuccessResponse generateResponse(boolean status) {
        AddSuperCategorySuccessResponse resp;
        if (status) {
            resp = new AddSuperCategorySuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddSuperCategorySuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
