package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class DeleteSuperCategory {

    private final String at;
    private String admin_id;
    private String type;
    private String super_category;

    public DeleteSuperCategory(String at, String super_category) {
        this.at = at;
        this.super_category = super_category;
    }

    public String getAt() {
        return at;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuper_category() {
        return super_category;
    }

    public void setSuper_category(String super_category) {
        this.super_category = super_category;
    }

}
