/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.AddAffiliate;
import java.sql.SQLException;
import java.util.HashSet;
import com.vixcart.admin.intfc.validation.AddAffiliateValidator;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @company techvay
 * @author rifaie
 */
public class AddAffiliateConstraints implements AddAffiliateValidator {

    private final AddAffiliate req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private static final String[] imgExts = {"png", "jpg", "bmp", "jpeg"};

    public AddAffiliateConstraints(AddAffiliate addTyp) throws Exception {
        this.req = addTyp;
        this.dbc = DB.getConnection();
        this.mdbc = DB.getMongoConnection();
    }

    @Override
    public String validateCompany() throws Exception {
        String valid = ErrMsg.ERR_COMPANY;
        String regX = RegX.REGX_COMPANY;
        String company = req.getCompany();
        if (validate(company, regX)) {
            if (dbc.checkCompany(company) == 0) {
                valid = CorrectMsg.CORRECT_COMPANY;
            } else {
                valid = ErrMsg.ERR_COMPANY_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateLink() throws Exception {
        String valid = ErrMsg.ERR_LINK;
        String urlString = req.getLink();
        try {
            URL url = new URL(urlString);
            if (dbc.checkLink(urlString)) {
                valid = CorrectMsg.CORRECT_LINK;
            } else {
                valid = ErrMsg.ERR_LINK_EXISTS;
            }
        } catch (MalformedURLException ex) {

        }
        return valid;
    }

    @Override
    public String validateFileName() throws Exception {
        String valid = ErrMsg.ERR_FILE_NAME;
        String regX = RegX.REGX_FILE_NAME;
        String fileName = req.getFileName();
        ArrayList<String> exts = new ArrayList<>(Arrays.asList(imgExts));
        if (validate(fileName, regX)) {
            String ext = fileName.substring(fileName.lastIndexOf(".")+1);
            if (exts.contains(ext)) {
                valid = CorrectMsg.CORRECT_FILE_NAME;
            } else {
                valid = ErrMsg.ERR_FILE_NAME_EXT;
            }
        }
        return valid;
    }

    @Override
    public String validateFileSize() throws Exception {
        String valid = ErrMsg.ERR_FILE_SIZE;
        long fileSize = req.getFileSize();
        if (fileSize>1024&fileSize<10240) {
            valid = CorrectMsg.CORRECT_FILE_SIZE;
        } else if(fileSize<1024) {
            valid = ErrMsg.ERR_FILE_SIZE_SMALL;
        }else{
            valid = ErrMsg.ERR_FILE_SIZE_LARGE;            
        }
        return valid;
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = req.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        AdminID admin = mdbc.getAdminID(at);
        if (!admin.getProfile_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            if (dbc.checkNBAdminID(admin.getProfile_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                req.setAdmin_id(admin.getProfile_id());
                req.setUtype(admin.getType());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public String validateUserType(String adminType) throws Exception {
        String valid = ErrMsg.ERR_ADMIN_TYPE;
        HashSet<String> types = dbc.getSubTypes(req.getUtype());
        if (types.contains(adminType)) {
            valid = CorrectMsg.CORRECT_ADMIN_TYPE;
        }
        return valid;
    }

    @Override
    public boolean validate(String value, String regX) {
        boolean valid = false;
        if (value.matches(regX)) {
            valid = true;
        }
        return valid;
    }

    @Override
    public void closeConnection() throws SQLException {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

}
