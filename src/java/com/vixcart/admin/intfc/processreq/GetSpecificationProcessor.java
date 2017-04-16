package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface GetSpecificationProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean getSpecification() throws Exception;

}
