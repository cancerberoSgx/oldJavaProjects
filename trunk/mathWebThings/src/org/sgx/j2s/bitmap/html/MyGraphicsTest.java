package org.sgx.j2s.bitmap.html;

import org.sgx.j2s.widget.base.Color;

public class MyGraphicsTest {

	public static void main(String[] args) {
		MyGraphics g = new MyGraphics(2,2,40,40);
		g.setColor(Color.BLACK);
		g.drawRect(2,2,20,20);
		g.paint();
	}

}
