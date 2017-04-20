/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.support.controller;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.DBConnect;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class BlockAdminUser {

    private final String admin_id;
    private final DBConnect dbc;

    public BlockAdminUser(String admin_id) throws ClassNotFoundException, SQLException {
        this.admin_id = admin_id;
        this.dbc = DB.getConnection();
    }

    public boolean block() throws SQLException {
        boolean flag = false;
        if (dbc.blockAdmin(admin_id)) {
            flag = true;
        }
        dbc.closeConnection();
        return flag;
    }
}
