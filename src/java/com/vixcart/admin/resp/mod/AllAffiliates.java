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
public class AllAffiliates {
    
    private final String affiliate_id;
    private final String company;
    private final String website;
    private final String logo_path;
    private final String status;

    public AllAffiliates(String affiliate_id, String company, String website,String logo_path, String status) {
        this.affiliate_id = affiliate_id;
        this.company = company;
        this.website = website;
        this.logo_path = logo_path;
        this.status = status;
    }

    public String getAffiliate_id() {
        return affiliate_id;
    }

    public String getCompany() {
        return company;
    }

    public String getWebsite() {
        return website;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public String getStatus() {
        return status;
    }
    
    @Override
    public String toString() {
        return "{\"aid\":\"" + affiliate_id + "\", \"cmpn\":\"" + company + "\", \"ws\":\"" + website + "\",\"lp\":\""+logo_path+"\",\"status\":\""+status+"\"}";
    }
    
}
