package org.sgx.j2s.util.impl.test;

import org.sgx.j2s.util.List;
import org.sgx.j2s.util.impl.LinkedList;
import org.sgx.j2s.util.impl.Utils;

public class LinkedListTest {

	
	public static void main(String[] args) {
		Utils.cleanTests();
		new LinkedListTest().testAll();
		System.out.println(Utils.printAssertsFailed());
	}

	public  void testAll() {
		test2();
	}

	private void test2() {
		String [] content = {"cero", "uno", "dos", "tres", "cuatro", "cinco"};
		List <String> l = new LinkedList<String>();
		Utils.assertTrue(l.isEmpty(), "list.isEmpty1");
		Utils.assertTrue(l.size()==0, "list.size1");
		l.append("no");
		Utils.assertTrue(!l.isEmpty(), "list.isEmpty2");
		Utils.assertTrue(l.size()==1, "list.size2");
		l.remove("no");
		Utils.assertTrue(l.isEmpty(), "list.isEmpty3");
		Utils.assertTrue(l.size()==0, "list.size3");
//		for(int i=0)
		l.append("uno");
		Utils.assertTrue(l.size()==1, "list.size4");
		String [] content2 = {"cero", "uno", "dos", "tres", "cuatro", "cinco"};
		List <String> l2 = Utils.toList(content2);
		l.addAll(l2);
		Utils.assertTrue(l.size()==7, "list.addAll1");
		Utils.assertTrue(l2.size()==6, "list.addAll2");
	}
	
}
