package org.sgx.j2s.util.impl.test;

import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.util.impl.Utils;

public class UtilsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Utils.assertTrue(Utils.getUnique()!=Utils.getUnique(), "2");
		System.out.println("fin - is js : "+JsUtils.isJs());
		System.out.println(Utils.printAssertsFailed()+Utils.getUnique());
	}

}
