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
public class GetTotalPremiumPayments {
    private final String at;
    private final String company;
    private final String pageNo;
    private final String maxEntries;
    private String admin_id;
    private String type;

    public GetTotalPremiumPayments(String at, String company, String pageNo, String maxEntries) {
        this.at = at;
        this.company = company;
        this.pageNo = pageNo;
        this.maxEntries = maxEntries;
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

    public String getCompany() {
        return company;
    }

    public String getPageNo() {
        return pageNo;
    }

    public String getMaxEntries() {
        return maxEntries;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getType() {
        return type;
    }
}

