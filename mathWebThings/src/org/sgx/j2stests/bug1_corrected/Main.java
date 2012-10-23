package org.sgx.j2stests.bug1_corrected;

public class Main {
public static void main(String [] a) {
		
		//both next callings fail with java2ascript console message:
		//[Java2Script] Error in loading static/bin/org/sgx/j2stests/bug1/Class1$1.js!
		
		test_static();
//		new Class1("").test_dynamic();
		
	}


//	private void test_dynamic() {
//		new Class1("hello") {
//			public void method1() {
//				System.out.println("msg: "+attr1);
//			}
//		};
//	}


	private static void test_static() {
		new Class1("hello") {
			public void method1() {
				System.out.println("msg: "+attr1);
			}
		}.method1();
	}

}
