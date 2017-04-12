/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

import java.util.ArrayList;

/**
 * @company techvay
 * @author rifaie
 */
public class UserIds {
    private ArrayList<String> uids;

    public UserIds() {
        uids = new ArrayList<>();
    }
    
    public void addUserID(String uid){
        uids.add(uid);
    }
    
    public int size(){
        return uids.size();
    }

    @Override
    public String toString() {
        String response = "";
        response = uids.stream().map((String type) -> type+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        return "\"UserIds\":["+response+"]";
    }
    
    
    
}
