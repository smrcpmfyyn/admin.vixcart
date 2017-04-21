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
public class AffiliateRequest {
    private final String rid;
    private final String company;
    private final String website;
    private final String contactPerson;
    private final String email;
    private final String mobile;
    private final String date;
    private final String lastUpdate;
    private final String status;

    public AffiliateRequest(String rid, String company, String website, String contactPerson, String email, String mobile, String date, String lastUpdate, String status) {
        this.rid = rid;
        this.company = company;
        this.website = website;
        this.contactPerson = contactPerson;
        this.email = email;
        this.mobile = mobile;
        this.date = date;
        this.lastUpdate = lastUpdate;
        this.status = status;
    }

    public String getRid() {
        return rid;
    }
    
    @Override
    public String toString() {
        return "{\"rid\":\"" + rid +"\", \"company\":\"" + company +"\", \"website\":\"" + website+"\", \"contactPerson\":\"" + contactPerson+"\", \"email\":\"" + email+"\", \"mobile\":\"" + mobile+"\", \"date\":\"" + date+"\", \"lastUpdate\":\"" + lastUpdate +  "\", \"status\":\"" + status +"\"}";
    }

}
