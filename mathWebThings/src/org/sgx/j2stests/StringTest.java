package org.sgx.j2stests;

import org.sgx.j2s.utils.Utils;

public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test1();
		test2();
		System.out.println(Utils.printAssertsFailed());
	}
	private static void test2(){
		Boolean b = new Boolean(true);
		Utils.assertTrue(b, "Boolean 1 ");
		b = new Boolean(false);
		Utils.assertTrue(!b, "Boolean 2 ");
	}
	private static void test1() {
		String s= "abra";
		Utils.assertTrue(s.replaceFirst("a", "").equals("bra"), "replace first : ");
	}

}
