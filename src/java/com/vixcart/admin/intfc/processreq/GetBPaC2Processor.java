package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetBPaC2Processor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getBPaCs() throws Exception;

}
