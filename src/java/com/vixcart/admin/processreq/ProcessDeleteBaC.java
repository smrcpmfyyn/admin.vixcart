package com.vixcart.admin.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.DeleteBaCProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.DeleteBaC;
import com.vixcart.admin.resp.mod.DeleteBaCSuccessResponse;
import java.util.Random;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProcessDeleteBaC implements DeleteBaCProcessor{
    
    private final DeleteBaC req;
    private final MongoConnect mdbc;
    private final DBConnect dbc;
    private String accessToken;

    public ProcessDeleteBaC(DeleteBaC req) throws Exception {
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
    public boolean deleteBaC() throws Exception {
        return dbc.deleteBaC(req);
    }

    @Override
    public DeleteBaCSuccessResponse processRequest() throws Exception {
        DeleteBaCSuccessResponse obj = null;
        if (generateToken()) {
            if (deleteBaC()) {
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
    public DeleteBaCSuccessResponse generateResponse(boolean status) {
        DeleteBaCSuccessResponse resp;
        if (status) {
            resp = new DeleteBaCSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new DeleteBaCSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}
