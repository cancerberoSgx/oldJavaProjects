package org.sgx.j2s.utils;

import java.util.LinkedList;
import java.util.List;

public class JsObject {
public Object _native;

public static JsObject createFromArray(Object[][]arr) {
	return new JsObject(JsUtils.buildJsObject(arr));
}
public static JsObject createFromNative(Object o) {
	return new JsObject(o);
}
public JsObject(Object _native) {
	super();
	this._native = _native;
}
/**
 * @j2sNative
 * this._native[k]
 */
public Object get(Object k){return null;}
/**
 * @j2sNative
 * this._native[k]=v
 */
public void put(Object k, Object v){}
public List<Object>keySet() {
	List<Object> l = new LinkedList<Object>();
	//TODO
	return l;
}
}
