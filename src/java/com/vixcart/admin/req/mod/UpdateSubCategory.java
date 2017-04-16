package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateSubCategory {

    private String admin_id;
    private String type;
    private final String at;
    private final String subcat;
    private final String on_status;
    private final String off_status;
    

    /**
     *
     * @param at
     * @param subcat
     * @param on_status
     * @param off_status
     */
    public UpdateSubCategory(String at, String subcat, String on_status, String off_status) {
        this.at = at;
        this.subcat = subcat;
        this.on_status = on_status;
        this.off_status = off_status;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getType() {
        return type;
    }

    public String getAt() {
        return at;
    }

    public String getSubcat() {
        return subcat;
    }

    public String getOn_status() {
        return on_status;
    }

    public String getOff_status() {
        return off_status;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    
}
