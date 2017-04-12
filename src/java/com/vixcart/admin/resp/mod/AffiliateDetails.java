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
public class AffiliateDetails {
    private final String company;
    private final String status;
    private final String mpp;
    private final String cf;
    

    public AffiliateDetails(String company, String mpp, String cf, String status) {
        this.company = company;
        this.status = status;
        this.mpp = mpp;
        this.cf = cf;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "{\"company\":\"" + company +"\", \"mpp\":\"" + mpp +"\", \"cf\":\"" + cf +  "\", \"status\":\"" + status +"\"}";
    }

    
}
