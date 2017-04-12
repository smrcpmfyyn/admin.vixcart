/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.intfc.validation;

/**
 *
 * @author rifaie
 */
public interface AddUserValidator extends EmpReportValidator{
    
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    public String validateName() throws Exception;
    
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    public String validateDesignation() throws Exception;
    
    /**
     *
     * @return
     * @throws Exception
     */
    public String validateAddress1() throws Exception;
    
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    public String validateAddress2() throws Exception;
    
    /**
     *
     * @return
     * @throws Exception
     */
    public String validatePlace() throws Exception;
    
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    public String validatePin() throws Exception;
    
    /**
     *
     * @return
     * @throws Exception
     */
    public String validateUtype() throws Exception;
    
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    public String validateUName() throws Exception;
    
    /**
     *
     * @return
     * @throws Exception
     */
    public String validateEmail() throws Exception;
}
