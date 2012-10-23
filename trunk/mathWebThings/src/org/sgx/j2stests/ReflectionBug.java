package org.sgx.j2stests;

import java.lang.reflect.Method;

/**
 * @j2sRequireImport java.lang.reflect.Modifier
 *
 */
public class ReflectionBug {
	public static void main(String[] args) {
		test1();
		System.out.println("finnished");
	}
	private static void test1() {
		Method[] mts = ReflectionBug.class.getMethods();
//		new ReflectionBug().getClass().getMethods();
//		for (int i = 0; i < mts.length; i++) {
//			System.out.println(mts[i].getReturnType()+" "+mts[i].getName());
//		}
	}	
}
