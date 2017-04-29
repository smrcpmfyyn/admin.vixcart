package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetSpecification {

    private final String at;
    private String admin_id;
    private String type;
    private final String specId;

    public GetSpecification(String at, String specId) {
        this.at = at;
        this.specId = specId;
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

    public String getSpecid() {
        return this.specId;
    }
}
