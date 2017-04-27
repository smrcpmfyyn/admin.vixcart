package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class ProductType {
    
    private final String id;
    private final String ptype;
    private final String online_visibility;
    private final String offline_visibility;


    public String getId() {
        return id;
    }

    public String getPtype() {
        return ptype;
    }

    public String getOnline_visibility() {
        return online_visibility;
    }

    public String getOffline_visibility() {
        return offline_visibility;
    }

    public ProductType() {
        this.id = "invalid";
        this.ptype = "invalid";
        this.online_visibility = "invalid";
        this.offline_visibility = "invalid";
    }

    public ProductType(String id,String ptype, String online_visibility, String offline_visibility) {
        this.id = id;
        this.ptype = ptype;
        this.online_visibility = online_visibility;
        this.offline_visibility = offline_visibility;
    }
    
    public String getProductTypes(){
        return "{\"" + "id\":\"" + id +  "\",\"ptype\":\"" + ptype + "\"}"; 
    }

    @Override
    public String toString() {
        return "{\"" + "id\":\"" + id + "\",\"ptype\":\"" + ptype + "\",\"online_visibilty_status\":\"" + online_visibility + "\",\"offline_visibility_status\":\"" + offline_visibility + "\"}";
    }
    

}
