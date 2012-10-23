package org.sgx.j2s.bitmap.canvas;

import org.sgx.j2s.widget.base.Color;

public class CanvasBitmapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CanvasBitmap canvas = new CanvasBitmap(100,100,100,100);
		canvas.line(2,2,40,40, Color.BLACK);
		canvas.paint(3,3,Color.BLACK);
	}

}
