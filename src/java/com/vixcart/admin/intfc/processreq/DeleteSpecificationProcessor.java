package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface DeleteSpecificationProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean deleteSpecification() throws Exception;

}
