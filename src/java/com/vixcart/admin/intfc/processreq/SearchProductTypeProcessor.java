package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface SearchProductTypeProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean searchProductType() throws Exception;

}
