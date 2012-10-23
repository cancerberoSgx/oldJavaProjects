package org.sgx.j2s.utils.test;

import org.sgx.j2s.utils.JsUtils;

public class JsUtilsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		bindJsFunction_test1();
//		handlers_test1();
		buildJsObjectTest();
		
	}
	private static void buildJsObjectTest() {
		Object[][]arr={{"name", "seba"},{"address","Abcd 1234"}};
		Object jsObj = JsUtils.buildJsObject(arr);
		System.out.println(JsUtils.dumpObject(jsObj));
	}
	private static void handlers_test1() {
		Runnable r = new Runnable(){
			@Override
			public void run() {
				System.out.println(" java_handler() : this is java handler");
			}
		};
		JsUtils.registerHandler("h1", r);
		/**
		 * @j2sNative
		 * window.onresize=org.sgx.j2s.utils.JsUtils._handlers.get("h1").run;
		 */{}
		
	}
	private static void bindJsFunction_test1() {
		JsUtils.bindJsFunction("window.onresize", JsUtilsTest.class.getName()+".java_handler");
	}
	public static void java_handler() {
		System.out.println(" java_handler() : this is java handler");
	}
}
