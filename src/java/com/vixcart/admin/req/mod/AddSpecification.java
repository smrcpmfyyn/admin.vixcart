package com.vixcart.admin.req.mod;

// <editor-fold defaultstate="collapsed" desc="packages">

import java.util.Arrays;
import java.util.List;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class AddSpecification {

    private final String at;
    private String admin_id;
    private String type;
    private final String pType;
    private final List<String> specific;

    public AddSpecification(String at, String pType, String[] specific) {
        this.at = at;
        this.pType = pType;
        this.specific = Arrays.asList(specific);
    }

    public String getAt() {
        return at;
    }

    public String getpType() {
        return pType;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_id() {
        return this.admin_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public List<String> getSpecific() {
        return this.specific;
    }
}
