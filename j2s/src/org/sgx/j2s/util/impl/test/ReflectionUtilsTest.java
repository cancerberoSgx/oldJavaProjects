package org.sgx.j2s.util.impl.test;

import org.sgx.j2s.Patches;
import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.model.util.*;
import org.sgx.j2s.util.*;
import org.sgx.j2s.util.impl.ObjectUtils;
import org.sgx.j2s.util.impl.Utils;

public class ReflectionUtilsTest  {

	public void testjavabeansyhings() {
		JavaUtilsTestBean bean = new JavaUtilsTestBean("hola", 3);
		
		Collection<String> props = ModelHelper.getPropertyNames(bean);
		Utils.assertTrue(props.size()==2, "testjavabeansyhings1");
		
		Object p1Val = ModelHelper.get(bean, "prop1");
		Utils.assertTrue((p1Val instanceof String) && ((String)p1Val).equals("hola"), "testjavabeansyhings2");
		
		ModelHelper.set(bean, "prop1", "como anda");
		Object p1Val2 = ModelHelper.get(bean, "prop1");
		Utils.assertTrue((p1Val2 instanceof String) && ((String)p1Val2).equals("como anda"), "testjavabeansyhings3");
		
		int iii=3;
			
		
	}
	
	public static void main(String[] args)  {
		new Patches().runPatches();
		Utils.cleanTests();
		ReflectionUtilsTest test = new ReflectionUtilsTest();
		test.testjavabeansyhings();
		test.testTransforms();
		System.out.println(Utils.printAssertsFailed());		
	}

	private void testTransforms() {
		ObjectUtilsTestBean b = (ObjectUtilsTestBean) ObjectUtils.transformArrToBean(
				new Object[][]	{
						{"foo", "cero"},
						{"integer", 111},
						{"real", 1.1}, 
						{"bool1", true}
				}, 
					ObjectUtilsTestBean.class);
		Utils.assertTrue(b.getFoo().equals("cero"), "transformArrToBean1");
		Utils.assertTrue(b.getInteger().equals(111), "transformArrToBean2");
		Utils.assertTrue(b.getReal().equals(1.1), "transformArrToBean3");
		Utils.assertTrue(b.getBool1().equals(new Boolean(true)), "transformArrToBean4");
		Utils.assertTrue(new Boolean(true).equals(true), "booooo");
		System.out.println(JsUtils.dumpObject(new Boolean(false)));
	}

}
