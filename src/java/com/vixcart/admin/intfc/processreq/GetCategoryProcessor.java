package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetCategoryProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getCategory() throws Exception;

}
