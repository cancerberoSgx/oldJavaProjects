package org.sgx.j2s.util.impl;

public class DefaultErrorListener implements ErrorListener {

	public void notify(ErrorInfo e) {
		System.out.println("****************ERROR**********************");
		System.out.println("* MSG  : "+e.msg);
		System.out.println("* URL  : "+e.url);
		System.out.println("* LINE : "+e.line);
		System.out.println("*******************************************");
		
	}

}
