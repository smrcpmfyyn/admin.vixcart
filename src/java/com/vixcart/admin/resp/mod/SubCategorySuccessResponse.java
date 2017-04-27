package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">

import java.util.ArrayList;



// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class SubCategorySuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<SubCategory> allSubCategories;

    public SubCategorySuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allSubCategories = new ArrayList<>();
    }

    public SubCategorySuccessResponse(String status, String accessToken, ArrayList<SubCategory> allSubTypes) {
        this.status = status;
        this.accessToken = accessToken;
        this.allSubCategories = allSubTypes;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<SubCategory> getAllSubCategories() {
        return allSubCategories;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"subCategories\":[ ";
        response = allSubCategories.stream().map((SubCategory type) -> type.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
