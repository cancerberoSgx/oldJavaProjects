package org.sgx.j2s.bitmap;
/**
 * 
 * @author SGURIN
 *
 */
public class JsStringBuffer {
Object b;
public JsStringBuffer() {
	/**
	 * @j2sNative
	 * this.b=[];
	 */{}
}
public void append(String s) {
	/**
	 * @j2sNative
	 * this.b.push(s);
	 */{}
}
/**
 * @j2sNative
 * return this.b.join("");
 */
public String toString() {
	return null;
}
}
