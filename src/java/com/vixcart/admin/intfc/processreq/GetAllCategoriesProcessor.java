package com.vixcart.admin.intfc.processreq;

/**
 *
 * @author vinu
 */
public interface GetAllCategoriesProcessor extends Processor{
        
    public boolean generateToken() throws Exception;
    
    public void getAllCategories() throws Exception;

}
