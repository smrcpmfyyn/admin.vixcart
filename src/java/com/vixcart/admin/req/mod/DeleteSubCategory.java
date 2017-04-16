package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class DeleteSubCategory {

    private final String at;
    private String admin_id;
    private String type;
    private String Sub_category;

    public DeleteSubCategory(String at, String Sub_category) {
        this.at = at;
        this.Sub_category = Sub_category;
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

    public String getSub_category() {
        return Sub_category;
    }

    public void setSub_category(String Sub_category) {
        this.Sub_category = Sub_category;
    }

}
