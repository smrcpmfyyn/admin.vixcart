package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetAllBrandsProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetAllBrands;
import com.vixcart.admin.resp.mod.Brand;
import com.vixcart.admin.resp.mod.GetAllBrandsSuccessResponse;
import java.util.ArrayList;
import java.util.Random;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetAllBrands implements GetAllBrandsProcessor {

    private final GetAllBrands req;
    private ArrayList<Brand> res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetAllBrands(GetAllBrands req) throws Exception {
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
    public boolean getAllBrands() throws Exception {
        res = dbc.getAllBrands(req.getNo(), req.getOffset());
        return true;
    }

    @Override
    public GetAllBrandsSuccessResponse processRequest() throws Exception {
        GetAllBrandsSuccessResponse obj = null;
        if (generateToken()) {
            if (getAllBrands()) {
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
    public GetAllBrandsSuccessResponse generateResponse(boolean status) {
        GetAllBrandsSuccessResponse resp;
        if (status) {
            resp = new GetAllBrandsSuccessResponse(ResponseMsg.RESP_OK, accessToken, res);
        } else {
            resp = new GetAllBrandsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
