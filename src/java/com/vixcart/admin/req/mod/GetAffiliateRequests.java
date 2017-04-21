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
public class GetAffiliateRequests {
    private final String at;
    private String admin_id;
    private String utype;
    private final String status;
    private final String pageNo;
    private final String maxEntries;

    public GetAffiliateRequests(String at, String pageNo, String maxEntries, String status) {
        this.at = at;
        this.pageNo = pageNo;
        this.maxEntries = maxEntries;
        this.status = status;
    }

    public String getPageNo() {
        return pageNo;
    }

    public String getMaxEntries() {
        return maxEntries;
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

    public String getStatus() {
        return status;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }
    
}
