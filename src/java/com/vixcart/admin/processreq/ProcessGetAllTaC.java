package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.GetAllTaCProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.GetAllTaC;
import com.vixcart.admin.resp.mod.GetAllTaCSuccessResponse;
import com.vixcart.admin.resp.mod.TaC;
import java.util.ArrayList;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessGetAllTaC implements GetAllTaCProcessor{
    
    private final GetAllTaC req;
    private ArrayList<TaC> res;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessGetAllTaC(GetAllTaC req) throws Exception {
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
    public boolean getAllTaC() throws Exception {
        res= dbc.getAllTaC(req.getNo(), req.getOffset());
        return true;
    }

    @Override
    public GetAllTaCSuccessResponse processRequest() throws Exception {
        GetAllTaCSuccessResponse obj = null;
        if (generateToken()) {
            if (getAllTaC()) {
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
    public GetAllTaCSuccessResponse generateResponse(boolean status) {
        GetAllTaCSuccessResponse resp;
        if (status) {
            resp = new GetAllTaCSuccessResponse(ResponseMsg.RESP_OK, accessToken, res);
        } else {
            resp = new GetAllTaCSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
        dbc.closeConnection();
    }

}
