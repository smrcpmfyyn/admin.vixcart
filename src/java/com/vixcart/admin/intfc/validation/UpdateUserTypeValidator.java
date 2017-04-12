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
public interface UpdateUserTypeValidator extends EmpReportValidator{
    
    public String validateTypeID() throws Exception;
    
    public String validateType() throws Exception;
    
    public String validateSuperType() throws Exception;
}
