package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetAllProductTypesProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getAllProductTypes() throws Exception;

}
