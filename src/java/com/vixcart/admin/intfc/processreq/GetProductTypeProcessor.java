package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetProductTypeProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getProductType() throws Exception;

}
