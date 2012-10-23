package org.sgx.j2s.bitmap.html;

import org.sgx.j2s.widget.base.Color;

public class MyBitmapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyBitmap b = new MyBitmap(50,50,200,200);
		b.paint(30,30,Color.BLACK);
		b._gr.paint();
	}

}
