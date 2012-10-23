package org.sgx.j2s.bitmap.html;

import org.sgx.j2s.bitmap.Bitmap;
import org.sgx.j2s.canvas.Canvas;
import org.sgx.j2s.canvas.Canvas2DContext;
import org.sgx.j2s.widget.base.Color;
/**
 * 
 * @author SGURIN
 *
 */
public class MyBitmap implements Bitmap{
	public MyGraphics _gr;

	int x, y, w, h;

	public MyBitmap(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		init();
	}

	private void init() {
		_gr = new MyGraphics(x,y,w,h);
	}

	public void paint(int x, int y, Color c) {
		_gr.setColor(c);
		_gr.drawRect(x,y,1,1);
	}

	public void erase(Color c) {
		_gr.clear();
	}

	public void line(int x1, int y1, int x2, int y2, Color c) {
		_gr.setColor(c);
		_gr.drawLine(x1, y1, x2, y2);
	}

	public void flush() {
		_gr.paint();
	}

	public Object getRepresentation() {
		return _gr.canvasEl;
	}

}
