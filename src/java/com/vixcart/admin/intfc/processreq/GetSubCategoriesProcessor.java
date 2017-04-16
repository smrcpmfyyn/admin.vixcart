package com.vixcart.admin.intfc.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public interface GetSubCategoriesProcessor extends Processor{
  
    public boolean generateToken() throws Exception;
    
    public void getAllSubCategories() throws Exception;
  

}
