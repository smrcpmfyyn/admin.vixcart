package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetTaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getTaC() throws Exception;

}
