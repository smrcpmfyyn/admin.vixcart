package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetSubCategoryProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getSubCategory() throws Exception;

}
