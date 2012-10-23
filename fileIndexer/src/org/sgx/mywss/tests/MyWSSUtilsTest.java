package org.sgx.mywss.tests;

import java.util.HashMap;
import java.util.Map;

import org.sgx.j2s.utils.Utils;
import org.sgx.mywss.MYWSSUtils;
import org.sgx.mywss.model.Method;

import static org.sgx.mywss.MYWSSUtils.java2WSType;;

public class MyWSSUtilsTest {

	public static void main(String[] args) {
//		test_java2WSType();
		test_toJSON();
	}

	private static void test_toJSON() {
		Map obj1 = Utils.toMap(new Object[][]{
				{"attr1", Utils.toMap(new Object[][]{
						{"atr1",1},{"atr2","string"}, {"atr3", Utils.toList(new Object[]{"uno"})},{"atr4",new HashMap()}
				})},
				
				{"attr2","string1"}
		});
		System.out.println(MYWSSUtils.toJSON(obj1));
	}

	private static void test_java2WSType() {
		Utils.assertTrue(java2WSType("".getClass()).equals(Method.TYPE_STRING), "test_java2WSType1");
	}
	
	

}
