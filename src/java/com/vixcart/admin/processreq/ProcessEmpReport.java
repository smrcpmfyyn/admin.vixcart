/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.processreq;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import com.vixcart.admin.intfc.processreq.EmpReportProcessor;
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.req.mod.EmpReport;
import com.vixcart.admin.resp.mod.EmpReportSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessEmpReport implements EmpReportProcessor {

    private final EmpReport empR;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessEmpReport(EmpReport empR) throws Exception {
        this.empR = empR;
        this.mdbc = DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = empR.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(empR.getAdmin_id(), accessToken);
    }

    @Override
    public String generateReport() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpReportSuccessResponse processRequest() throws Exception {
        EmpReportSuccessResponse resp;
        if (generateToken()) {
            resp = generateResponse(true);
        } else {
            resp = generateResponse(false);
        }
        return resp;
    }

    @Override
    public EmpReportSuccessResponse generateResponse(boolean status) {
        EmpReportSuccessResponse resp;
        if (status) {
            resp = new EmpReportSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new EmpReportSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        mdbc.closeConnection();
    }

}
