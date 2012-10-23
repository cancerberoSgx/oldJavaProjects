package org.sgx.j2s.util.impl;

public class ErrorInfo {
	public Object msg, url, line;

	public ErrorInfo(Object msg, Object url, Object l) {
		super();
		this.msg = msg;
		this.url = url;
		this.line = l;
	}
}
