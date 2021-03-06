package com.vixcart.admin.intfc.validation;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public interface UpdateCategoryValidator extends EmpReportValidator {

    public String validateCategory() throws Exception;

    public String validateOnlineVisibilityStatus() throws Exception;

    public String validateOfflineVisibilityStatus() throws Exception;

}
