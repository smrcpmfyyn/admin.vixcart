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
public interface GetActivePremiumPaymentsValiadtor extends EmpReportValidator {
 
    public String validateCompany() throws Exception;
    public String validateOffset() throws Exception;
    
}
