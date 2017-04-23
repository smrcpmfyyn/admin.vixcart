package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateProductType {

    private final String at;
    private String admin_id;
    private String type;
    private final String pTypeid;
    private final String pType;
    private final String on_status;
    private final String off_status;

    public String getAt() {
        return at;
    }

    public String getPType() {
        return pType;
    }

    public String getpTypeid() {
        return pTypeid;
    }

    public String getpType() {
        return pType;
    }

    
    
    public UpdateProductType(String at, String pTypeid, String pType, String on_status, String off_status) {
        this.at = at;
        this.pTypeid = pTypeid;
        this.pType = pType;
        this.on_status = on_status;
        this.off_status = off_status;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_id() {
        return this.admin_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String getOn_status() {
        return this.on_status;
    }

    public String getOff_status() {
        return this.off_status;
    }
}
