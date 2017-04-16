package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface UpdateSpecificationProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean updateSpecification() throws Exception;

}
