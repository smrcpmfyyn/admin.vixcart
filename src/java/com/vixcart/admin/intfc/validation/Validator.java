/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.intfc.validation;

import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */

public interface Validator {

    /**
     *
     * @param value
     * @param regX
     * @return
     */
    public boolean validate(String value,String regX);

    /**
     *
     * @throws SQLException
     */
    public void closeConnection() throws SQLException;
}
