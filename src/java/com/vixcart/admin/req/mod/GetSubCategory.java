package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetSubCategory{

	 private final String at;
	 private String admin_id;
	 private String type;
	 private final String subCategId;
	public GetSubCategory(String at, String subCategId) {
        	this.at = at;
		this.subCategId = subCategId;
}

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAt() {
      return  this.at;
    }

    public String getAdmin_id() {
      return  this.admin_id;
    }

    public String getType() {
      return  this.type;
    }

    public String getSubcategid() {
      return  this.subCategId;
    }
}