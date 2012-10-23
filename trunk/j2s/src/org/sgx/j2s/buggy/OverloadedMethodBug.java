package org.sgx.j2s.buggy;

public class OverloadedMethodBug {


	private void m1() {
	}
	private void m1(int j) {
	}
	private void m1(char j) {
	}
	private void m1(String j) {
	}
	public void enter() {
		m1();
		m1(10);
		m1('c');
		m1("c");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OverloadedMethodBug o = new OverloadedMethodBug();
		o.enter();
	}

}
