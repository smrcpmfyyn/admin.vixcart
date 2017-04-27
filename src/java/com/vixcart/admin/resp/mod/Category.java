package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class Category {

    private String category_id;
    private String category;
    private String super_category;
    private String online_visibilty_status;
    private String offline_visibility_status;

    public Category() {
        this.category_id = "invalid";
        this.category = "invalid";
        this.super_category = "invalid";
        this.online_visibilty_status = "invalid";
        this.offline_visibility_status = "invalid";
    }

    public Category(String category_id, String category, String super_category, String online_visibilty_status, String offline_visibility_status) {
        this.category_id = category_id;
        this.category = category;
        this.super_category = super_category;
        this.online_visibilty_status = online_visibilty_status;
        this.offline_visibility_status = offline_visibility_status;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSuper_category() {
        return super_category;
    }

    public void setSuper_category(String super_category) {
        this.super_category = super_category;
    }

    public String getOnline_visibilty_status() {
        return online_visibilty_status;
    }

    public void setOnline_visibilty_status(String online_visibilty_status) {
        this.online_visibilty_status = online_visibilty_status;
    }

    public String getOffline_visibility_status() {
        return offline_visibility_status;
    }

    public void setOffline_visibility_status(String offline_visibility_status) {
        this.offline_visibility_status = offline_visibility_status;
    }
    
    public String getCategories(){
        return "{\"" + "category_id\":\"" + category_id +  "\",\"category\":\"" + category + "\"}";
    }

    @Override
    public String toString() {
        return "{\"" + "category_id\":\"" + category_id + "\",\"category\":\"" + category + "\",\"super_category\":\"" + super_category + "\",\"online_visibilty_status\":\"" + online_visibilty_status + "\",\"offline_visibility_status\":\"" + offline_visibility_status + "\"}";
    }
    
    
}
