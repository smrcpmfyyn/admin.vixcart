package com.vixcart.admin.intfc.processreq;


/**
 *
 * @author vinu
 */
public interface LoadSpecificationsProcessor extends Processor {

    public boolean generateToken() throws Exception;

    public boolean loadSpecifications() throws Exception;

}
