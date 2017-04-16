package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateCategory {

    private final String at;
    private final String categ;
    private final String on_status;
    private final String off_status;
    private String admin_id;
    private String type;
    
    public UpdateCategory(String at, String categ, String on_status, String off_status) {
        this.at = at;
        this.categ = categ;
        this.on_status = on_status;
        this.off_status = off_status;
    }

    public String getAt() {
        return at;
    }

    public String getCateg() {
        return categ;
    }

    public String getOn_status() {
        return on_status;
    }

    public String getOff_status() {
        return off_status;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getType() {
        return type;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

}
