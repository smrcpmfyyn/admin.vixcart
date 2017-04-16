package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface AddProductTypeProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean addProductType() throws Exception;

}
