package com.vixcart.admin.intfc.processreq;

/**
 *
 * @author vinu
 */
public interface GetSuperCategoriesProcessor extends Processor{
    
    public boolean generateToken() throws Exception;
    
    public void getAllSuperCategories() throws Exception;
    
}
