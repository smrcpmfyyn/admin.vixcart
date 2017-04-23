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
public class GetMembers {
    private final String at;
    private String admin_id;
    private String utype;
    private final String query;
    private String queryType;
    private final String maxEntries;
    private final String pageNo;

    public GetMembers(String at, String query, String maxEntries, String pageNo) {
        this.at = at;
        this.query = query;
        this.maxEntries = maxEntries;
        this.pageNo = pageNo;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
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

    public String getQuery() {
        return query;
    }

    public String getQueryType() {
        return queryType;
    }

    public String getMaxEntries() {
        return maxEntries;
    }

    public String getPageNo() {
        return pageNo;
    }
}
