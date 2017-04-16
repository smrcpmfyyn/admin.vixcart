package com.vixcart.admin.intfc.processreq;

/**
 *
 * @author vinu
 */
public interface AddSuperCategoryProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean addSuperCategory() throws Exception;

}
