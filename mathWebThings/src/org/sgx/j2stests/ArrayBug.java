package org.sgx.j2stests;

/** this is not a bug!!!!*/
public class ArrayBug {
	
	public static void main(String[] args) {
		Object o1 = new String[]{"1,1", "1,2", "1,3"};
		if((o1 instanceof Object[][])) 
			System.out.println("bug1"); //Object[] is not an Object[][]
		System.out.println("end tests");
	}
	
}
