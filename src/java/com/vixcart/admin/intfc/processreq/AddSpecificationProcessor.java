package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface AddSpecificationProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean addSpecification() throws Exception;

}
