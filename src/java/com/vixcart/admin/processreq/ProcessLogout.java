/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.processreq;

import com.vixcart.admin.db.DB;
import com.vixcart.admin.db.MongoConnect;
import com.vixcart.admin.hash.Hashing;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessLogout {
    private final String at;
    private final MongoConnect mdbc;

    public ProcessLogout(String at) throws Exception {
        this.at = at;
        this.mdbc = DB.getMongoConnection();
    }
    
    public String logout() throws Exception{
        mdbc.logout(at);
        mdbc.closeConnection();
        Random ran = new Random();
        String ts = at + ran.nextLong();
        return Hashing.genAccessToken(ts);
    }
}
