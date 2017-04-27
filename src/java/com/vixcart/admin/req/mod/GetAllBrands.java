package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetAllBrands {

    private final String at;
    private String admin_id;
    private String type;
    private final String maxEntries;
    private final String pageNo;

    public GetAllBrands(String at,String no, String offset) {
        this.at = at;
        this.pageNo = no;
        this.maxEntries = offset;
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

    public String getMaxEntries() {
        return maxEntries;
    }

    public String getPageNo() {
        return pageNo;
    }
    
}
