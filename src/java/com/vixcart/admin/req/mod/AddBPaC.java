/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.req.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class AddBPaC {
    private final String at;
    private String admin_id;
    private String type;
    private final String brand;
    private final String tac;

    public AddBPaC(String at, String brand, String tac) {
        this.at = at;
        this.brand = brand;
        this.tac = tac;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAt() {
        return at;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getTac() {
        return tac;
    }
}
