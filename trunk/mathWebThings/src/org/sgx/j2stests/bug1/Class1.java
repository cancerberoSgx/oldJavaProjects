package org.sgx.j2stests.bug1;

public class Class1 implements Interface1 {

	String attr1;
	public Class1(String attr1) {
		super();
		this.attr1 = attr1;
	}


	@Override
	public void method1() {
		// TODO Auto-generated method stub

	}
	
	
	public static void main(String [] a) {
		
		//both next callings fail with java2ascript console message:
		//[Java2Script] Error in loading static/bin/org/sgx/j2stests/bug1/Class1$1.js!
		
		test_static();
		new Class1("").test_dynamic();
		
	}


	private void test_dynamic() {
		new Class1("hello") {
			public void method1() {
				System.out.println("msg: "+attr1);
			}
		}.method1();
	}


	private static void test_static() {
		new Class1("hello") {
			public void method1() {
				System.out.println("msg: "+attr1);
			}
		}.method1();
	}

}
