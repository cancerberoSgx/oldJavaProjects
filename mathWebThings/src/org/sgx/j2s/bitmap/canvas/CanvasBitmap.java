package org.sgx.j2s.bitmap.canvas;

import org.sgx.j2s.bitmap.Bitmap;
import org.sgx.j2s.canvas.Canvas;
import org.sgx.j2s.canvas.Canvas2DContext;
import org.sgx.j2s.html.myApi.DomUtils;
import org.sgx.j2s.widget.base.Color;

public class CanvasBitmap implements Bitmap{
	int x, y, w, h;
	private Canvas canvas;
	private Canvas2DContext ctx;
	
	public CanvasBitmap(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		init();
	}
	
	private void init() {
		canvas = Canvas.create(x, y, w, h);
		ctx=canvas.getContext();
		
	}

	public void paint(int x, int y, Color c) {
		ctx.fillStyle=c.toCSS();
		ctx.fillRect(x,y,1,1);
	}

	public void erase(Color c) {
		String oldFillStyle = ctx.fillStyle;
		ctx.fillStyle=c.toCSS();
		ctx.fillRect(0,0,w-1,h-1);
		ctx.fillStyle=oldFillStyle;
	}

	public void line(int x1, int y1, int x2, int y2, Color c) {
		System.out.println("line");
		ctx.beginPath();
		ctx.strokeStyle=c.toCSS();
		ctx.moveTo(x1, y1);
		ctx.lineTo(x2, y2);
//		ctx.closePath();	
		ctx.fill();
		ctx.stroke();
	}

	public void flush() {
	}
/**
 * return s the pure html <canvas> element 
 */
	public Object getRepresentation() {
		return DomUtils.getDocument().getElementById(canvas.getHTMLElementId());
	}
}
