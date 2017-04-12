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
public class UserDetails {
    private final String name;
    private final String designation;
    private final String address1;
    private final String address2;
    private final String place;
    private final String pin;
    private final String type;
    private final String email;
    private final String uname;
    private final String status;

    public UserDetails(String name, String designation, String address1, String address2, String place, String pin, String type, String email, String uname, String status) {
        this.name = name;
        this.designation = designation;
        this.address1 = address1;
        this.address2 = address2;
        this.place = place;
        this.pin = pin;
        this.type = type;
        this.email = email;
        this.uname = uname;
        this.status = status;
    }

    public String getUname() {
        return uname;
    }
    
    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\", \"designation\":\"" + designation + "\",\"address1\":\"" + address1 + "\",\"address2\":\"" + address2 + "\",\"place\":\"" + place + "\",\"pin\":\"" + pin + "\",\"type\":\"" + type + "\",\"email\":\"" + email + "\",\"uname\":\"" + uname + "\", \"status\":\"" + status +"\"}";
    }
}
