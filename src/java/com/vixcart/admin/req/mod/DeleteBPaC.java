package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class DeleteBPaC{

	 private final String at;
	 private String admin_id;
	 private String type;
	 private final String brand;
	 private final String categ;
	 private final String ptype;
	public DeleteBPaC(String at, String brand, String categ, String ptype) {
        	this.at = at;
		this.brand = brand;
		this.categ = categ;
		this.ptype = ptype;
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
      return  this.ptype;
    }
}