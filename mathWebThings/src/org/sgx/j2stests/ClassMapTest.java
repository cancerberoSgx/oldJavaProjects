package org.sgx.j2stests;

import java.util.HashMap;
import java.util.Map;

import org.sgx.j2s.utils.LWMap;
import org.sgx.j2s.utils.Utils;

public class ClassMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		javaUtilMapTest();
	}

	private static void javaUtilMapTest() {
		Map<Class, Object> m = new HashMap<Class, Object>();
		m.put(Integer.class, "Integer");
		m.put(new Integer(2).getClass(), "Integer2");
		m.put(String.class, "String");
		m.put("".getClass(), "String2");
		m.put(new ClassMapTest().getClass(), "ClassMapTest");
		m.put(Comparable.class, "Comparable");
		
//		m.put(new Comparable<Object>(){ //anonym class
//			public int compareTo(Object o) {return 0;}			
//		}.getClass(), "Comparable");
		Comparable<Object> comp1 = new Comparable<Object>(){ //anonym class
			public int compareTo(Object o) {return 0;}			
		};
		Utils.assertTrue(m.get(Integer.class).equals("Integer2"), "test1 1");
		Utils.assertTrue(m.get(String.class).equals("String2"), "test1 2");
		Utils.assertTrue(m.get(ClassMapTest.class).equals("ClassMapTest"), "test1 3");
		Utils.assertTrue(m.get(Comparable.class).equals("Comparable") &&
					m.get(comp1.getClass()).equals("Comparable"), "test1 4");
		System.out.println(Utils.printAssertsFailed()+ClassMapTest.class);
	}
	
	private static void LWMapTest() {
		LWMap<Class, Object> m = new LWMap<Class, Object>();
		m.put(Integer.class, "Integer");
		m.put(new Integer(2).getClass(), "Integer2");
		m.put(String.class, "String");
		m.put("".getClass(), "String2");
		m.put(new ClassMapTest().getClass(), "ClassMapTest");
		m.put(Comparable.class, "Comparable");
		
//		System.out.println(Utils.)
		
//		m.put(new Comparable<Object>(){ //anonym class
//			public int compareTo(Object o) {return 0;}			
//		}.getClass(), "Comparable");
		Comparable<Object> comp1 = new Comparable<Object>(){ //anonym class
			public int compareTo(Object o) {return 0;}			
		};
		Utils.assertTrue(m.get(Integer.class).equals("Integer2"), "test1 1");
		Utils.assertTrue(m.get(String.class).equals("String2"), "test1 2");
		Utils.assertTrue(m.get(ClassMapTest.class).equals("ClassMapTest"), "test1 3");
		Utils.assertTrue(m.get(Comparable.class).equals("Comparable") &&
					m.get(comp1.getClass()).equals("Comparable"), "test1 4");
		System.out.println(Utils.printAssertsFailed()+ClassMapTest.class);
	}

}
