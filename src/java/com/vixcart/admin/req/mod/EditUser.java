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
public class EditUser {
    private final String at;
    private String admin_id;
    private String type;
    private final String id;
    private final String name;
    private final String designation;
    private final String address1;
    private final String address2;
    private final String place;
    private final String pin;
    private final String utype;
    private String userType;
    private String updatedAdminId;

    public EditUser(String at, String id, String name, String designation, String address1, String address2, String place, String pin, String utype) {
        this.at = at;
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.address1 = address1;
        this.address2 = address2;
        this.place = place;
        this.pin = pin;
        this.utype = utype;
    }

    public String getUpdatedAdminId() {
        return updatedAdminId;
    }

    public void setUpdatedAdminId(String updatedAdminId) {
        this.updatedAdminId = updatedAdminId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPlace() {
        return place;
    }

    public String getPin() {
        return pin;
    }

    public String getUtype() {
        return utype;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
