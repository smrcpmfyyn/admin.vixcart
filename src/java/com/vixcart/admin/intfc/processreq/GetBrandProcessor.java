package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetBrandProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getBrand() throws Exception;

}
