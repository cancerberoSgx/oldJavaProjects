package org.sgx.j2s.utils.test;

import java.util.Map;

import org.sgx.j2s.utils.ModelHelper;
import org.sgx.j2s.utils.Utils;

public class ModelHelperTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test1();
		System.out.println(Utils.printAssertsFailed());
	}

	private static void test1() {
		/*int i, char c, String s, double d, float f,
		boolean b, Integer ii*/
		ModelHelperTestBean bean = new ModelHelperTestBean(1,'c',"str", 1.123, (float) 1.123, true, 123);
		Map<String, Class> props = ModelHelper.getProperties(bean);
		Utils.assertTrue(props.keySet().size()==7, "props.keySet().size()==7 : "+props.keySet().size());
		System.out.println(Utils.pringMap(props));
	}

}
