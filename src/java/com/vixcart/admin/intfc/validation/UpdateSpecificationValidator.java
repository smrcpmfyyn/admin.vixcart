package com.vixcart.admin.intfc.validation;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public interface UpdateSpecificationValidator extends EmpReportValidator{
    public String validatePType() throws Exception;

    public String validateSpecific() throws Exception;

    public String validateOn_status() throws Exception;

    public String validateOff_status() throws Exception;

}