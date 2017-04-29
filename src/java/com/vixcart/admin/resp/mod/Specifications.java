package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class Specifications {
    private final String id;
    private final String ptype;
    private final String spec;
    private final String onStatus;
    private final String offStatus;
    private final String fltrStatus;

    public Specifications() {
        this.id = "invalid";
        this.ptype = "invalid";
        this.spec = "invalid";
        this.onStatus = "invalid";
        this.offStatus = "invalid";
        this.fltrStatus = "invalid";
    }

    public Specifications(String id, String ptype ,String spec, String onStatus, String offStatus, String fltrStatus) {
        this.id = id;
        this.ptype = ptype;
        this.spec = spec;
        this.onStatus = onStatus;
        this.offStatus = offStatus;
        this.fltrStatus = offStatus;
    }

    public String getId() {
        return id;
    }

    public String getSpec() {
        return spec;
    }

    public String getOnStatus() {
        return onStatus;
    }

    public String getOffStatus() {
        return offStatus;
    }

    public String getFltrStatus() {
        return fltrStatus;
    }

    public String getPtype() {
        return ptype;
    }
    
    public String loadSpec(){
        return "{\"" + "id\":\"" + id + "\",\"spec\":\"" + spec + "\",\"online_visibilty_status\":\"" + onStatus + "\",\"offline_visibility_status\":\"" + offStatus+ "\",\"filter_visibility_status\":\"" + fltrStatus + "\"}";
    }
    
    @Override
    public String toString() {
        return "{\"" + "id\":\"" + id + "\",\"ptype\":\"" + ptype+"\",\"spec\":\"" + spec + "\",\"online_visibilty_status\":\"" + onStatus + "\",\"offline_visibility_status\":\"" + offStatus+ "\",\"filter_visibility_status\":\"" + fltrStatus + "\"}";
    }

}
