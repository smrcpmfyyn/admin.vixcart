package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetBPaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getBPaC() throws Exception;

}
