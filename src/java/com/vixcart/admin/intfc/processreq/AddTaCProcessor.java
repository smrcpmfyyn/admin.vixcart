package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface AddTaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean addTaC() throws Exception;

}
