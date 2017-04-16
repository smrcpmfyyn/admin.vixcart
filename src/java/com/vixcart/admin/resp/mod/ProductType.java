package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProductType {
    
    String id;
    String ptype;
    String online_visibility;
    String offline_visibility;

    public ProductType() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getOnline_visibility() {
        return online_visibility;
    }

    public void setOnline_visibility(String online_visibility) {
        this.online_visibility = online_visibility;
    }

    public String getOffline_visibility() {
        return offline_visibility;
    }

    public void setOffline_visibility(String offline_visibility) {
        this.offline_visibility = offline_visibility;
    }

    public ProductType(String id, String ptype, String online_visibility, String offline_visibility) {
        this.id = id;
        this.ptype = ptype;
        this.online_visibility = online_visibility;
        this.offline_visibility = offline_visibility;
    }

    

}
