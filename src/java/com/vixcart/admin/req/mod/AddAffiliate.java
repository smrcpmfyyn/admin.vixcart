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
public class AddAffiliate {
    private final String at;
    private String admin_id;
    private String utype;
    private final String company;
    private final String link;
    private final String fileName;
    private final long fileSize;

    public AddAffiliate(String at, String company, String link, String fileName, long fileSize) {
        this.at = at;
        this.company = company;
        this.link = link;
        this.fileName = fileName;
        this.fileSize = fileSize;
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

    public String getCompany() {
        return company;
    }

    public String getLink() {
        return link;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }
    
    
}
