package org.sgx.j2s.util.impl.test;

import org.sgx.j2s.util.List;
import org.sgx.j2s.util.impl.Utils;

public class IteratorTest {

	public static void main(String[] args) {
		Utils.cleanTests();		
		test1();
		System.out.println(Utils.printAssertsFailed() );
	}
	
	private static void test1() {
		String[] classesArr = new String[] {"1", "2", "3"};
		List<String> classes = new Utils<String>().toList_(classesArr);
		int i=0;
		for(String c : classes){
			Utils.assertTrue(Integer.parseInt(c)==i+1, "IteratorTest test1 "+i);
			i++;
		}

	}

}
