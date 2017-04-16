package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class DeleteTaC {

    private final String at;
    private String admin_id;
    private String type;
    private final String taC;

    public DeleteTaC(String at, String taC) {
        this.at = at;
        this.taC = taC;
    }

    public String getAt() {
        return at;
    }

    public String getTaC() {
        return taC;
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
}
