package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class SuperCategory {

    private String super_category_id;
    private String super_category;
    private String online_visibilty_status;
    private String offline_visibilty_status;

    public SuperCategory(String super_category_id, String super_category, String online_visibilty_status, String offline_visibilty_status) {
        this.super_category_id = super_category_id;
        this.super_category = super_category;
        this.online_visibilty_status = online_visibilty_status;
        this.offline_visibilty_status = offline_visibilty_status;
    }

    public String getSuper_category_id() {
        return super_category_id;
    }

    public String getSuper_category() {
        return super_category;
    }

    public String getOnline_visibilty_status() {
        return online_visibilty_status;
    }

    public String getOffline_visibilty_status() {
        return offline_visibilty_status;
    }
    
    public String getSuperCategories(){
        return "{\"" + "s_c_id\":\"" + super_category_id +  "\",\"s_categ\":\"" + super_category + "\"}";
    }

    @Override
    public String toString() {
        return "{\"super_category_id\":\"" + super_category_id + "\", \"super_category\":\"" + super_category + "\",\"online_visibilty_status\":\"" + online_visibilty_status + "\",\"offline_visibilty_status\":\"" + offline_visibilty_status + "\"}";
    }

}
