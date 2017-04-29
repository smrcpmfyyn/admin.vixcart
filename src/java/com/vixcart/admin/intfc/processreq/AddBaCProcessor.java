package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface AddBaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean addBPaC() throws Exception;

}
