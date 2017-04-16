package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface DeleteBaCProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean deleteBaC() throws Exception;

}
