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
public class PremiumPayments {
    
    private final String company;
    private final String amount;
    private final String date;
    private final String reference;
    private final String status;

    public PremiumPayments(String company, String amount, String date, String reference, String status) {
        this.company = company;
        this.amount = amount;
        this.date = date;
        this.reference = reference;
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getReference() {
        return reference;
    }

    public String getStatus() {
        return status;
    }
    
    @Override
    public String toString() {
        return "{\"cmp\":\"" + company + "\", \"amt\":\"" + amount + "\", \"ref\":\"" + reference + "\", \"dt\":\"" + date + "\",\"st\":\"" + status + "\"}";
    }
    
}
