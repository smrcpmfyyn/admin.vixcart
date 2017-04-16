package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class AddSubCategory {

    private final String at;
    private String admin_id;
    private String utype;
    private final String category;
    private final String subCategory;

    public AddSubCategory(String at, String category, String subCategory) {
        this.at = at;
        this.category = category;
        this.subCategory = subCategory;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getAt() {
        return at;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getUtype() {
        return utype;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

}
