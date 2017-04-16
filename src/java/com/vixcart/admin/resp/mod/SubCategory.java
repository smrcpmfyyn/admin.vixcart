package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class SubCategory {

    private String sub_category_id;
    private String sub_category;
    private String category;
    private String online_visibilty_status;
    private String offline_visibility_status;

    public SubCategory(String sub_category_id, String sub_category, String category, String online_visibilty_status, String offline_visibility_status) {
        this.sub_category_id = sub_category_id;
        this.sub_category = sub_category;
        this.category = category;
        this.online_visibilty_status = online_visibilty_status;
        this.offline_visibility_status = offline_visibility_status;
    }

    public String getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(String sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public String toString() {
        return "{\"" + "sub_category_id\":\"" + sub_category_id + "\",\"sub_category\":\"" + sub_category + "\",\"category\":\"" + category + "\",\"online_visibilty_status\":\"" + online_visibilty_status + "\",\"offline_visibility_status\":\"" + offline_visibility_status + "\"}\"";
    }

}
