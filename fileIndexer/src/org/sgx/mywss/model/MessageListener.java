package org.sgx.mywss.model;

import org.sgx.mywss.MessageNotUnderstoodException;
/**
 * (server side) 
 * @author sgurin
 *
 */
public interface MessageListener {
	String dispatchMessage(Message m) throws Exception;
	Method[]getMethods();

}
