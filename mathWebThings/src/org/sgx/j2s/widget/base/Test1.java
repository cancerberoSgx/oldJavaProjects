package org.sgx.j2s.widget.base;

public class Test1 {

	public static void main(String[] args) {
		colorTest1();
	}

	private static void colorTest1() {
		String colorStr = "rgb(2,22,222)";
		Color color = Color.parseColor(colorStr);
		System.out.println(color);
	}

}
