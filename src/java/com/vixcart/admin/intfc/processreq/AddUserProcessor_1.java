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
public interface AddUserProcessor_1 extends Processor{
    
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean generateToken() throws Exception;
    
    /**
     *
     * @throws java.lang.Exception
     */
    public void addUser() throws Exception;

    /**
     *
     * @param email
     * @param vkey
     * @param name
     * @throws java.lang.Exception
     */
    public void sendSetPasswordEmail(String email,String passwordToken, String name) throws Exception;
}
