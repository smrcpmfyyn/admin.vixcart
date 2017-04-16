package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetProductTypesProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getProductTypes() throws Exception;

}
