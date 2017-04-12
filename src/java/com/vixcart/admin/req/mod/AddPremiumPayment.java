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
public class AddPremiumPayment {
    private final String at;
    private String admin_id;
    private String utype;
    private final String company;
    private final String referenceNo;
    private final String amount;

    public AddPremiumPayment(String at, String company, String referenceNo, String amount) {
        this.at = at;
        this.company = company;
        this.referenceNo = referenceNo;
        this.amount = amount;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getAt() {
        return at;
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

    public String getReferenceNo() {
        return referenceNo;
    }

    public String getAmount() {
        return amount;
    }
}
