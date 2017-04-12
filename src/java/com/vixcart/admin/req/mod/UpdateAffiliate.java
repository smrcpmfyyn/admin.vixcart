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
public class UpdateAffiliate {
    private final String at;
    private final String ckCompany;
    private String admin_id;
    private String utype;
    private final String company;
    private final String status;
    private final String mpp;
    private final String cf;

    public UpdateAffiliate(String at, String ckCompany, String company, String status, String mpp, String cf) {
        this.at = at;
        this.ckCompany = ckCompany;
        this.company = company;
        this.status = status;
        this.mpp = mpp;
        this.cf = cf;
    }

    public String getAt() {
        return at;
    }

    public String getCkCompany() {
        return ckCompany;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getUtype() {
        return utype;
    }

    public String getCompany() {
        return company;
    }

    public String getStatus() {
        return status;
    }

    public String getMpp() {
        return mpp;
    }

    public String getCf() {
        return cf;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }
}
