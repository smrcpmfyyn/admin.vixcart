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
public class AffiliateUserDetails {
    private final String affiliate;
    private final String uname;
    private final String uid;
    private final String lg;
    private final String status;

    public AffiliateUserDetails(String affiliate, String uname, String uid, String lg, String status) {
        this.affiliate = affiliate;
        this.uid = uid;
        this.lg = lg;
        this.status = status;
        this.uname = uname;
    }
    
    @Override
    public String toString() {
        return "{\"company\":\"" + affiliate +"\", \"uname\":\"" + uname +"\", \"uid\":\"" + uid +"\", \"lg\":\"" + lg +  "\", \"status\":\"" + status +"\"}";
    }
}
