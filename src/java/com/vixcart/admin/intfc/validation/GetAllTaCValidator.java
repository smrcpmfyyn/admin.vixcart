package com.vixcart.admin.intfc.validation;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public interface GetAllTaCValidator extends EmpReportValidator {

    public String validateOffset() throws Exception;
    public String validateNo() throws Exception;

}
