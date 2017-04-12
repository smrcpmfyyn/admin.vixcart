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
public interface UpdateAffiliateValidator extends EmpReportValidator{
    
    public String validateCkCompany() throws Exception;
    
    public String validateCompany() throws Exception;
    
    public String validateStatus() throws Exception;
    
    public String validateMPP() throws Exception;
    
    public String validateCF() throws Exception;
}
