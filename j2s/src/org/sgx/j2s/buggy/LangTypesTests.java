package org.sgx.j2s.buggy;

import org.sgx.j2s.Patches;
import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.util.impl.Utils;

public class LangTypesTests {
	
	public static void main(String[] args) {
		LangTypesTests t = new LangTypesTests();
		new Patches().runPatches();
		t.classMethodNamesTest2();
		t.typeTest1();
		t.classObjectTest();
		System.out.println(Utils.printAssertsFailed());
		
//		System.out.println(JsUtils.dumpObject(Boolean.class));
	}

	public void classObjectTest() {
		Class[] classesArr = new Class[] {String.class, Double.class, 
				Float.class, Integer.class, Short.class, Long.class, Boolean.class, 
				Byte.class, Character.class, Object.class	};
		Object[] objsArr = {"", new Double(1.23), new Float(1.23), new Integer(123), 
				new Short((short) 123), new Long(123), new Boolean(true), new Byte((byte) 123), 
				new Character('v'), new Object()};
		Utils.assertTrue(classesArr.length==objsArr.length, "classObjectTest test0 ");
		
		String[] classMethodNames = {"getName", "getMethods"};
		for(int i=0; i<classesArr.length; i++) {
			for (int j = 0; j < classMethodNames.length; j++) {
				Utils.assertTrue(JsUtils.hasMethodNamed(classesArr[i], classMethodNames[j]), 
						"classMethodNames test1 "+classesArr[i]+" - "+classMethodNames[j]);
			}
			Utils.assertTrue(classesArr[i].equals(classesArr[i]), 
					"classMethodNames test2 "+classesArr[i]);
			Utils.assertTrue(classesArr[i].equals(objsArr[i].getClass()) && objsArr[i].getClass().equals(classesArr[i]), 
					"classMethodNames test3 "+classesArr[i]);
		}
	}
	
	public void classMethodNamesTest2() {
		Utils.assertTrue(Boolean.class.equals(Boolean.class),"classMethodNamesTest2 1");
		Utils.assertTrue("".getClass().equals("1".getClass()),"classMethodNamesTest2 2");
		Utils.assertTrue("".getClass().equals(String.class),"classMethodNamesTest2 3");
		Utils.assertTrue(!"".getClass().equals(Object.class),"classMethodNamesTest2 4");
		Utils.assertTrue(!"".getClass().equals(new Float(1.23f).getClass()),"classMethodNamesTest2 4");
	}
	
	private void typeTest1() {
		Float f = new Float(3.4f);
		Utils.assertTrue(f.equals(3.4f), "typesTest1");
		Utils.assertTrue(f.equals(new Float(3.4f)), "typesTest2");		
		Boolean b = new Boolean(true);
		Utils.assertTrue(b.equals(true), "typesTest3 "+b.toString()+ " - "+true+"");
		Utils.assertTrue(b.toString().equals(true+""), "typesTest4 ");		
	}
	
}
