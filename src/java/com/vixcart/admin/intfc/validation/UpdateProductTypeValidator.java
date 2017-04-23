package com.vixcart.admin.intfc.validation;

// <editor-fold defaultstate="collapsed" desc="packages">
// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public interface UpdateProductTypeValidator extends EmpReportValidator {

    public String validatePTypeId() throws Exception;

    public String validatePType() throws Exception;

    public String validateOn_status() throws Exception;

    public String validateOff_status() throws Exception;

}
