package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetSuperCategoryProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getSuperCategory() throws Exception;

}
