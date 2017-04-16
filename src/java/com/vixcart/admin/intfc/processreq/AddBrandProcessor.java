package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface AddBrandProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean addBrand() throws Exception;

}
