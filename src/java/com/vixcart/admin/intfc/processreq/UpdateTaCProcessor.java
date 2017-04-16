package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface UpdateTaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean updateTaC() throws Exception;

}
