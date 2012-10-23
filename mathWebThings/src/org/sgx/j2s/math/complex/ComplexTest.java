package org.sgx.j2s.math.complex;

public class ComplexTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test1();
		
	}

	private static void test1() {
		Complex c = new Complex(2,3);
		Complex c2 = c.mul(new Complex(4,3));
		System.out.println(c2);
	}

}
