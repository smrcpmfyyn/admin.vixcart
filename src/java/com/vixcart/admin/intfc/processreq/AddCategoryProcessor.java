package com.vixcart.admin.intfc.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public interface  AddCategoryProcessor extends Processor{
    
    public boolean generateToken() throws Exception;
    
    public boolean addCategory() throws Exception;

}
