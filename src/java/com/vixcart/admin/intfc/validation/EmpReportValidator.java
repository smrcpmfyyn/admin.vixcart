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
public interface EmpReportValidator extends Validator{
    public String validateAccessToken() throws Exception;
    public String validateUserType(String adminType) throws Exception;
}
