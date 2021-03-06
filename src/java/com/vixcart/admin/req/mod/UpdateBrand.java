package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateBrand{

	 private final String at;
	 private String admin_id;
	 private String type;
	 private final String brand;
	 private final String on_status;
	 private final String off_status;
	public UpdateBrand(String at, String brand, String on_status, String off_status) {
        	this.at = at;
		this.brand = brand;
		this.on_status = on_status;
		this.off_status = off_status;
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

    public String getOn_status() {
      return  this.on_status;
    }

    public String getOff_status() {
      return  this.off_status;
    }
}