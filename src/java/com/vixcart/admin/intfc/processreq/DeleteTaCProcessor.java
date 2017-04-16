package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface DeleteTaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean deleteTaC() throws Exception;

}
