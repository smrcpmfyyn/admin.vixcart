package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class AddTaC {

    private final String at;
    private String admin_id;
    private String type;
    private final String pType;
    private final String category;

    public AddTaC(String at, String pType, String category) {
        this.at = at;
        this.pType = pType;
        this.category = category;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_id() {
        return this.admin_id;
    }

    public String getAt() {
        return at;
    }

    public String getpType() {
        return pType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String getCategory() {
        return this.category;
    }
}
