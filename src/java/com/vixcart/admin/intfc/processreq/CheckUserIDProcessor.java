/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.intfc.processreq;

/**
 *
 * @author rifaie
 */
public interface CheckUserIDProcessor extends Processor{
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean generateToken() throws Exception;
    
    public boolean addUpdateUserIDActivity() throws Exception;
}
