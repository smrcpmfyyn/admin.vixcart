package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface DeleteBrandProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean deleteBrand() throws Exception;

}
