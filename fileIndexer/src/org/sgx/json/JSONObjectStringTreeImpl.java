package org.sgx.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class JSONObjectStringTreeImpl {
	private Object o;
	public JSONObjectStringTreeImpl(Object o) {
		this.o=o;
	}
	public boolean isArray() {
		return o.getClass().equals(java.util.ArrayList.class);
	}
	public Collection castToArray(){
		return (Collection) o;
	}
	public boolean isObject() {
		return o.getClass().equals(HashMap.class);
	}
}
