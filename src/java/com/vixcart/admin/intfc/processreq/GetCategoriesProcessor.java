package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetCategoriesProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getCategories() throws Exception;

}
