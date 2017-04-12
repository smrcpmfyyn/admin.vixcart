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
public class AffiliateCompany {
    private final String company;

    public AffiliateCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }
    
    @Override
    public String toString() {
        return "{\"cmpn\":\"" + company + "\"}";
    }
}
