package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface SearchBrandProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean searchBrand() throws Exception;

}
