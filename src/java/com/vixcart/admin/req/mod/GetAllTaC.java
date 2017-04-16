package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetAllTaC {

    private final String at;
    private String admin_id;
    private String type;
    private final String no;
    private final String offset;

    public GetAllTaC(String at,String no, String offset) {
        this.at = at;
        this.no = no;
        this.offset = offset;
    }

    public String getNo() {
        return no;
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

    public String getOffset() {
        return this.offset;
    }
}
