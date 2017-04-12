/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.validation;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.intfc.validation.FAUAValidator;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.regx.RegX;
import com.vixcart.admin.req.mod.ActivityFilter;
import com.vixcart.admin.req.mod.FAUA;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @company techvay
 * @author rifaie
 */
public class FAUAConstraints implements FAUAValidator {

    private final FAUA req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public FAUAConstraints(FAUA gu) throws Exception {
        this.req = gu;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateFilter() throws Exception {
        ActivityFilter ftr = req.getFtr();
        String valid = "";
        valid += validateUID(ftr) + "#";
        valid += validateUType(ftr) + "#";
        valid += validateActivity(ftr) + "#";
        valid += validateEntryStatus(ftr);
        return valid;
    }

    private String validateUID(ActivityFilter ftr) throws SQLException {
        String valid = ErrMsg.ERR_FTR_UID;
        String regX = RegX.REGX_DIGIT;
        if (ftr.getUid() != null) {
            String aid = ftr.getUid();
            if (validate(aid, regX)) {
                if (dbc.checkAdminID(aid)) {
                    valid = CorrectMsg.CORRECT_FTR_UID;
                } else {
                    valid = ErrMsg.ERR_FTR_UID_NOT_EXISTS;
                }
            }
        } else {
            valid = CorrectMsg.CORRECT_FTR_UID;
        }
        return valid;
    }
    
    private String validateUType(ActivityFilter ftr) throws SQLException {
        String valid = ErrMsg.ERR_FTR_UTYPE;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        if (ftr.getuType() != null) {
            String[] utypes = ftr.getuType();
            for (String utype : utypes) {
                if (validate(utype, regX)) {
                    if (dbc.checkType(utype) == 1) {
                        valid = CorrectMsg.CORRECT_FTR_UTYPE;
                    } else {
                        valid = ErrMsg.ERR_FTR_UTYPE_NOT_EXISTS;
                        break;
                    }
                } else {
                    valid = ErrMsg.ERR_FTR_UTYPE_NOT_EXISTS;
                    break;
                }
            }
        } else {
            valid = CorrectMsg.CORRECT_FTR_UTYPE;
        }
        return valid;
    }

    private String validateActivity(ActivityFilter ftr) throws SQLException {
        String valid = ErrMsg.ERR_FTR_ACTIVITY;
        String regX = RegX.REGX_ACTIVITY;
        if (ftr.getActivity() != null) {
            String[] activities = ftr.getActivity();
            for (String activity : activities) {
                if (validate(activity, regX)) {
                    if (dbc.checkActivity(activity)) {
                        valid = CorrectMsg.CORRECT_FTR_ACTIVITY;
                    } else {
                        valid = ErrMsg.ERR_FTR_ACTIVITY_NOT_EXISTS;
                        break;
                    }
                } else {
                    valid = ErrMsg.ERR_FTR_ACTIVITY_NOT_EXISTS;
                    break;
                }
            }
        } else {
            valid = CorrectMsg.CORRECT_FTR_ACTIVITY;
        }
        return valid;
    }

    private String validateEntryStatus(ActivityFilter ftr) {
        String valid = ErrMsg.ERR_ENTRY_STATUS;
        ArrayList<String> al = new ArrayList<>();
        al.add("valid");
        al.add("invalid");
        al.add("blocked");
        if (ftr.getEntryStatus() != null) {
            String[] status = ftr.getEntryStatus();
            for (String stat : status) {
                if (al.contains(stat)) {
                    valid = CorrectMsg.CORRECT_FTR_ENTRY_STATUS;
                }else{
                    valid = ErrMsg.ERR_ENTRY_STATUS;
                    break;
                }
            }
        } else {
            valid = CorrectMsg.CORRECT_FTR_ENTRY_STATUS;
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
    }

    
}
