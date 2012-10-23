package org.sgx.j2s.buggy;

public class OverloadError {
public int m1(String[]s) {
	return 1;
}
public int m1(String s) {
	return 2;
}
public int i1(int[]s) {
	return 1;
}
public int i1(int s) {
	return 2;
}
public int i1(Object s) {
	return 3;
}

	public static void main(String[] args) {
		OverloadError e = new OverloadError();
		String[ ]a = new String[]{"",""};
		boolean b = (  e.m1(a)==1 && e.m1("str")==2 ); 
		System.out.println(b);
		b = (  e.i1(new int[]{1,2})==1 && e.i1(1)==2 ); 
		System.out.println(b);
	}

}
