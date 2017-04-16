package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateSuperCategory {

    private final String at;
    private String admin_id;
    private String type;
    private String super_category;
    private String on_status;
    private String off_status;

    public UpdateSuperCategory(String at, String super_category, String on_status, String off_status) {
        this.at = at;
        this.super_category = super_category;
        this.on_status = on_status;
        this.off_status = off_status;
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

    public String getOn_status() {
        return on_status;
    }

    public void setOn_status(String on_status) {
        this.on_status = on_status;
    }

    public String getOff_status() {
        return off_status;
    }

    public void setOff_status(String off_status) {
        this.off_status = off_status;
    }

    public String getAt() {
        return at;
    }
    
    
}
