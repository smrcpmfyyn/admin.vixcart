/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.req.mod;

import com.vixcart.admin.jsn.JSONParser;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @company techvay
 * @author rifaie
 */
public class FAfUA {
    private final String at;
    private String admin_id;
    private String utype;
    private final AffiliateActivityFilter ftr;
    private final int maxEntries;
    private final int pageNo;

    public FAfUA(String at, JSONObject ftr, String maxEntries, String pageNo) throws IOException, JSONException {
        this.at = at;
        this.maxEntries = Integer.parseInt(maxEntries);
        this.pageNo = Integer.parseInt(pageNo);
        if (ftr.length() == 0 || ftr == null) {
            this.ftr = new AffiliateActivityFilter();
        } else {
            this.ftr = JSONParser.parseJSONAfF(ftr.toString());
        }
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

    public AffiliateActivityFilter getFtr() {
        return ftr;
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    public int getPageNo() {
        return pageNo;
    }
}
