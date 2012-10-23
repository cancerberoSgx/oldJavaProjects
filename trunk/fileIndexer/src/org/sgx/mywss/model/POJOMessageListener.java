package org.sgx.mywss.model;

import org.sgx.mywss.MYWSSUtils;

public class POJOMessageListener extends AbstractMessageListener {

	static Method[] methods;
	private Object pojo;
	public POJOMessageListener(Object pojo) throws Exception {
		this.pojo=pojo;
		init();
	}
	private void init() throws Exception {
		methods = MYWSSUtils.java2WSMethods(pojo);
	}
	
	@Override
	public Method[] getMethods() {
		return methods;
	}

}
