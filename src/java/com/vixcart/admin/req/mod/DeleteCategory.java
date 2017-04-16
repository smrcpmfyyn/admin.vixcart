package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class DeleteCategory {

    private final String at;
    private String admin_id;
    private String type;
    private String category;

    public DeleteCategory(String at, String _category) {
        this.at = at;
        this.category = _category;
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

    public String getCategory() {
        return category;
    }

    public void set_category(String _category) {
        this.category = _category;
    }

}
