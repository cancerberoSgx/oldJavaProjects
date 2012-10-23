package org.sgx.mywss.model;
/**
 * client side
 * @author sgurin
 *
 */
public class Response {
	/**
	 * it is false if there was no error or a string in the case of error that describe it.In the later this.response is undefined. 
	 */
public Object error;
/**
 * in case of this.error==false, i keep the response object.
 */
public Object response;
}
