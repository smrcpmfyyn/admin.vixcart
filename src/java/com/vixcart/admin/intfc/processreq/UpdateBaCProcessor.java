package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface UpdateBaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean updateBaC() throws Exception;

}
