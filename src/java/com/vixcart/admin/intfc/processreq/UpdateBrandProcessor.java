package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface UpdateBrandProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean updateBrand() throws Exception;

}
