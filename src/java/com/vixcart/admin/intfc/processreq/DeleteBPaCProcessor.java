package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface DeleteBPaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean deleteBPaC() throws Exception;

}
