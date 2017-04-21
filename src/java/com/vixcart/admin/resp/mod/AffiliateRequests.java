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
public class AffiliateRequests {
    private final String reqId;
    private final String company;
    private final String website;
    private final String reqDate;
    private final String updateDate;
    private final String status;

    public AffiliateRequests(String reqId,String company, String website, String reqDate, String updateDate, String status) {
        this.reqId = reqId;
        this.company = company;
        this.website = website;
        this.reqDate = reqDate;
        this.updateDate = updateDate;
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "{\"company\":\"" + company +"\", \"website\":\"" + website +"\", \"reqDate\":\"" + reqDate+"\", \"updateDate\":\"" + updateDate +  "\", \"status\":\"" + status +"\"}";
    }
}
