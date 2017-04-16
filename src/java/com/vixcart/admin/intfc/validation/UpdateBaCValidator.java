package com.vixcart.admin.intfc.validation;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public interface UpdateBaCValidator extends EmpReportValidator{
    public String validateBrand() throws Exception;

    public String validateCateg() throws Exception;

    public String validatePtype() throws Exception;

    public String validateOn_status() throws Exception;

    public String validateOff_status() throws Exception;

}