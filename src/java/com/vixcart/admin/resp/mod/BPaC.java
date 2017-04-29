package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class BPaC {
    
    private String id;
    private String brand;
    private String ptype;
    private String categ;
    private String onStatus;
    private String offStatus;

    public BPaC(String id, String brand, String ptype, String categ, String onStatus, String offStatus) {
        this.id = id;
        this.brand = brand;
        this.ptype = ptype;
        this.categ = categ;
        this.onStatus = onStatus;
        this.offStatus = offStatus;
    }

    public BPaC() {
        this.id = "invalid";
        this.brand = "invalid";
        this.ptype = "invalid";
        this.categ = "invalid";
        this.onStatus = "invalid";
        this.offStatus = "invalid";
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getOnStatus() {
        return onStatus;
    }

    public void setOnStatus(String onStatus) {
        this.onStatus = onStatus;
    }

    public String getOffStatus() {
        return offStatus;
    }

    public void setOffStatus(String offStatus) {
        this.offStatus = offStatus;
    }
    
    public String getBPaCs(){
        return "{\"" + "id\":\"" + id + "\",\"ptype\":\"" + ptype + "\",\"categ\":\"" + categ + "\",\"online_visibilty_status\":\"" + onStatus + "\",\"offline_visibility_status\":\"" + offStatus + "\"}";
    }
    
    @Override
    public String toString() {
        return "{\"" + "id\":\"" + id + "\",\"brand\":\"" + brand+"\",\"ptype\":\"" + ptype+"\",\"categ\":\"" + categ + "\",\"online_visibilty_status\":\"" + onStatus + "\",\"offline_visibility_status\":\"" + offStatus+  "\"}";
    }
    
    

}
