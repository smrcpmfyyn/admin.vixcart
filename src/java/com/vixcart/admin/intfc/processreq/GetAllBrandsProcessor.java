package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetAllBrandsProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getAllBrands() throws Exception;

}
