package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface UpdateBPaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean updateBPaC() throws Exception;

}
