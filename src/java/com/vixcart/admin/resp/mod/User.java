/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class User {
    
    private final String id;
    private final String name;
    private final String type;
    private final String lastlogged;
    private final String status;

    public User(String name, String type, String lastlogged,String slno, String status) {
        this.name = name;
        this.type = type;
        this.lastlogged = lastlogged;
        this.id = slno;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLastlogged() {
        return lastlogged;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\", \"type\":\"" + type + "\", \"lastlogged\":\"" + lastlogged + "\",\"id\":\""+id+"\",\"status\":\""+status+"\"}";
    }
    
}
