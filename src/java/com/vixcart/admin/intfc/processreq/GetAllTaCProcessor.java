package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetAllTaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getAllTaC() throws Exception;

}
