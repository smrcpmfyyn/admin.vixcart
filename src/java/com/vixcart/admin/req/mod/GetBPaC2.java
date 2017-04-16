package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class GetBPaC2{

	 private final String at;
	 private String admin_id;
	 private String type;
	 private final String brand;
	 private final String categ;
	 private final String pType;
	public GetBPaC2(String at, String brand, String categ, String pType) {
        	this.at = at;
		this.brand = brand;
		this.categ = categ;
		this.pType = pType;
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

    public String getBrand() {
      return  this.brand;
    }

    public String getCateg() {
      return  this.categ;
    }

    public String getPtype() {
      return  this.pType;
    }
}