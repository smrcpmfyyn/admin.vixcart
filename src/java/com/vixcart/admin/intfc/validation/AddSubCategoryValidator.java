package com.vixcart.admin.intfc.validation;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public interface AddSubCategoryValidator extends EmpReportValidator{
 
    public String validateCategory() throws Exception;
    
    public String validateSubCategory() throws Exception;
}