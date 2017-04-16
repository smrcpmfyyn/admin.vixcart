package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class Brand {

    String id;
    String brand;
    String online_visibility;
    String offline_visibility;

    public Brand(String id, String brand, String online_visibility, String offline_visibility) {
        this.id = id;
        this.brand = brand;
        this.online_visibility = online_visibility;
        this.offline_visibility = offline_visibility;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getOnline_visibility() {
        return online_visibility;
    }

    public String getOffline_visibility() {
        return offline_visibility;
    }
    
    
}
