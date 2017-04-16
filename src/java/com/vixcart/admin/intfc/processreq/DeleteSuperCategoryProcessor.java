package com.vixcart.admin.intfc.processreq;

// <editor-fold defaultstate="collapsed" desc="packages">


// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public interface DeleteSuperCategoryProcessor extends Processor{

    public boolean generateToken() throws Exception;
    
    public boolean deleteSuperCategory() throws Exception;

}
