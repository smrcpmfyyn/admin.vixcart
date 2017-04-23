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
public class MemberDetails {
    private final String member_id;
    private final String name;
    private final String pum;
    private final String put;
    private final String ap;
    private final String status;

    public MemberDetails(String member_id, String name, String pum, String put, String ap, String status) {
        this.member_id = member_id;
        this.name = name;
        this.pum = pum;
        this.put = put;
        this.ap = ap;
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "{\"member_id\":\"" + member_id +"\", \"name\":\"" + name +"\", \"pum\":\"" + pum +"\", \"put\":\"" + put +"\", \"ap\":\"" + ap +  "\", \"status\":\"" + status +"\"}";
    }
}
