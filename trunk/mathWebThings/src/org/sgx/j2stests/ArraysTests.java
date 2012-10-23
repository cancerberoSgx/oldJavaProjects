package org.sgx.j2stests;

import org.sgx.j2s.utils.Utils;

/** this is not a bug!!!!*/
public class ArraysTests {
	
	public static boolean test1d(Object o) {
		return o instanceof Object[];
	}
	public static boolean test0d(Object o) {
		return o instanceof Object;
	}
	public static boolean test2d(Object o) {
		return o instanceof Object[][];
	}
	public static void main(String[] args) {
		String[][] s2 = new String[][]{{"1,1", "1,2", "1,3"}, {"2,1", "2,2", "2,3"}};
		String[] s1 = new String[]{"1,1", "1,2", "1,3"};
		String s0 = "";
		Utils.assertTrue(s2 instanceof String[][], "s2 instanceof String[][]");
		Utils.assertTrue(s1 instanceof String[], "!(s1 instanceof String[])");
		
		Utils.assertTrue(test1d(s1), "test1d(s1)");
		Utils.assertTrue(test1d(s2), "test1d(s2)"); //Object[][] is an Object[][]
		Utils.assertTrue(!test2d(s1), "!test2d(s1)"); //Object[] is not a Object[][]
		
		System.out.println(Utils.printAssertsFailed());
	}
	
}
