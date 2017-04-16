package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface UpdateProductTypeProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean updateProductType() throws Exception;

}
