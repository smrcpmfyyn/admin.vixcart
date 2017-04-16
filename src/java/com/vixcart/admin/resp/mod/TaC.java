package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class TaC {

    String id;
    String product_type;
    String category;
    String online_visibility;
    String offline_visibility;

    public TaC(String id, String product_type, String category, String online_visibility, String offline_visibility) {
        this.id = id;
        this.product_type = product_type;
        this.category = category;
        this.online_visibility = online_visibility;
        this.offline_visibility = offline_visibility;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
    
    
}
