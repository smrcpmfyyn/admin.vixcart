package com.vixcart.admin.intfc.validation;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public interface GetBPaC2Validator extends EmpReportValidator{
    public String validateBrand() throws Exception;

    public String validateCateg() throws Exception;

    public String validatePType() throws Exception;

}