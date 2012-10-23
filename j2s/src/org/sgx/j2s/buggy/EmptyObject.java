package org.sgx.j2s.buggy;

import org.sgx.j2s.js.JsUtils;

public class EmptyObject {

	public static void main(String[] args) {
//		test1();
		test2();
	}
	private static void test2() {
Object o = null;
String json = "({\"pepe\":\"fooo\"})";
/**
 * @j2sNative
 * function __Obj__() {}
 * var __javaObjectNames__ = ["equals", "hashCode", "getClass", "clone", "finalize", "notify","notifyAll", "wait"]
 * for(var i=0; i<__javaObjectNames__.length; i++)
 * 		__Obj__.prototype[__javaObjectNames__[i]]=null;
 * 
 * o = new  __Obj__();
 * System.out.println (org.sgx.j2s.js.JsUtils.dumpObject (o));
 * with (o) {
	  o=eval(json);
	}
	System.out.println (org.sgx.j2s.js.JsUtils.dumpObject (o));
 */{}			 
		System.out.println(JsUtils.dumpObject(o));
	}
	private static void test1() {
Object o = null;
/**
 * @j2sNative
 * //create a new class for not Object.prototype. 
 * function __Obj__() {
 * }
 * var __javaObjectNames__ = ["equals", "hashCode", "getClass", "clone", "finalize", "notify","notifyAll", "wait"]
 * for(var i=0; i<__javaObjectNames__.length; i++)
 * 		__Obj__.prototype[__javaObjectNames__[i]]=null;
 * o=new __Obj__();
 */{}			 
		System.out.println(JsUtils.dumpObject(o));
	}
	
	

}
