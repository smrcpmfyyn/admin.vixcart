/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.intfc.validation;

/**
 * @company techvay
 * @author rifaie
 */
public interface AddAffiliateUserValidator extends EmpReportValidator{
    
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    public String validateAffiliate() throws Exception;
    
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    public String validateName() throws Exception;
    
    /**
     *
     * @return
     * @throws Exception
     */
    public String validateEmail() throws Exception;
    
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    public String validateMobile() throws Exception;
    

}
