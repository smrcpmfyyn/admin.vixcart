package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetBrand {

    private final String at;
    private String admin_id;
    private String type;
    private final String brand;

    public GetBrand(String at, String brandid) {
        this.at = at;
        this.brand = brandid;
    }

    public String getBrand() {
        return brand;
    }

    
    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAt() {
        return this.at;
    }

    public String getAdmin_id() {
        return this.admin_id;
    }

    public String getType() {
        return this.type;
    }
}
