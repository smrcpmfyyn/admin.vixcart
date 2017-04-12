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
public class AllPremiumPayments {

    private final String company;
    private final String amount;
    private final String expiry;

    public AllPremiumPayments(String company, String amount, String expiry) {
        this.company = company;
        this.amount = amount;
        this.expiry = expiry;
    }

    public String getCompany() {
        return company;
    }

    public String getAmount() {
        return amount;
    }

    public String getExpiry() {
        return expiry;
    }

    @Override
    public String toString() {
        return "{\"cmp\":\"" + company + "\", \"amount\":\"" + amount + "\",\"exp\":\"" + expiry + "\"}";
    }

}
