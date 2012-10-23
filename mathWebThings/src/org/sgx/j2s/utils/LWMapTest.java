package org.sgx.j2s.utils;

import java.util.Iterator;
import java.util.Map;

public class LWMapTest {

	public static void main(String[] args) {
		test1();
		test2();
		System.out.println(Utils.printAssertsFailed());
	}

	private static void test2() {
		Object[][]data = new Object[][]{{"uno", 1}, {"dos", 2}, {"equals", 999}};
		Map m1 = Utils.toMap(data);
		LWMap m2 = Utils.toLWMap(data);
		Utils.assertTrue(equals(m2, m1), "test1");
	}

	public static boolean equals(LWMap m1, Map m2){
		Iterator i = m2.keySet().iterator();
		while(i.hasNext()) {
			Object key = i.next();
			if(!m1.get(key).equals(m2.get(key))) {
				return false;
			}
		}
		return true;
	}
	private static void test1() {
		LWMap<Integer, String> m = new LWMap<Integer, String>();
		m.put(1, "uno");
		Utils.assertTrue(m.get(1).equals("uno"), "test1");
		System.out.println();
	}

}
