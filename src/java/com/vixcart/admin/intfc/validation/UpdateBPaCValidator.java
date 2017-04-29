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
public interface UpdateBPaCValidator extends EmpReportValidator{
    public String validateBPaC() throws Exception;

    public String validateOn_status() throws Exception;

    public String validateOff_status() throws Exception;
}
