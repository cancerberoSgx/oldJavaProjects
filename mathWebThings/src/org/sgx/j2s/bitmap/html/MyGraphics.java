package org.sgx.j2s.bitmap.html;

import org.sgx.j2s.bitmap.DomUtils;
import org.sgx.j2s.widget.base.Color;

/**
 * the most quickly way of drawing points and lines in pure html i've found
 * 
 * @author SGURIN
 * 
 * @j2sPrefix
function checkbrowser(){
	this.b = document.body;
	this.dom = document.getElementById ? 1:0;
	this.ie = this.b && typeof this.b.insertAdjacentHTML != 'undefined';
	this.mozilla = typeof(document.createRange) != 'undefined' && typeof((document.createRange()).setStartBefore) != 'undefined';
}

function ___Graphics(container)
{
	this.bw = new checkbrowser();
	this.color = '#000000';
	this.backbuffer = [];
	//Drawing to div-element or to the document itself?
	if (typeof(container) == 'string' && container != '' && typeof(document.getElementById(container)) != 'undefined')
	{
		this.container = document.getElementById(container);
		
		this.clear = function() {this.container.innerHTML = "";this.backbuffer = [];}
		var paint_ie = function()
		{
			this.container.insertAdjacentHTML("BeforeEnd", this.backbuffer.join(""));
			this.backbuffer = [];
		}
		var paint_moz = function()
		{
			var r = document.createRange();
			r.setStartBefore(this.container);
			this.container.appendChild(r.createContextualFragment(this.backbuffer.join("")));
			this.backbuffer = [];
		}		
		this.paint = this.bw.ie ? paint_ie : paint_moz;
	}
	else{
		this.clear = function() {this.backbuffer = [];}
		this.paint = function() 
		{
			document.write(this.backbuffer.join(""));
			this.backbuffer = [];
		}
	}	
}

___Graphics.prototype.setPixel = function(x, y, w, h){
	this.backbuffer .push( '<div style="position:absolute;left:'+x+'px;top:'+y+'px;width:'+w+'px;height:'+h+'px;background-color:'+this.color+';overflow:hidden;"></div>');
}

___Graphics.prototype.drawLine = function(x1, y1, x2, y2){
	//Always draw from left to right. This makes the algorithm much easier...
	if (x1 > x2)	{
		var tmpx = x1; var tmpy = y1;
		x1 = x2; y1 = y2;
		x2 = tmpx; y2 = tmpy;
	}
	
	var dx = x2 - x1;	
	var dy = y2 - y1; var sy = 1;	
	if (dy < 0)	{
		sy = -1;
		dy = -dy;
	}
	
	dx = dx << 1; dy = dy << 1;
	if (dy <= dx)	{	
		var fraction = dy - (dx>>1);
		var mx = x1;
		while (x1 != x2)		{
			x1++;
			if (fraction >= 0)			{
				this.setPixel(mx, y1,x1-mx,1);
				y1 += sy;
				mx = x1;
				fraction -= dx;
			}
			fraction += dy;
		}
		this.setPixel(mx,y1,x1-mx,1);
	} 
	else 	{
		var fraction = dx - (dy>>1);
		var my = y1;
		if (sy > 0)		{		
			while (y1 != y2)			{
				y1++;
				if (fraction >= 0)				{
					this.setPixel(x1++, my,1,y1-my);
					my = y1;
					fraction -= dy;
				}
				fraction += dx;
			}	
			this.setPixel(x1,my,1,y1-my);
		}
		else		{
			while (y1 != y2)			{
				y1--;
				if (fraction >= 0)				{
					this.setPixel(x1++, y1,1,my-y1);
					my = y1;
					fraction -= dy;
				}
				fraction += dx;
			}	
			this.setPixel(x1,y1,1,my-y1);		
		}
	}
};
 */
public class MyGraphics {

	static int idCounter=0;
	static String getNewId() {
		idCounter++;
		return "canvas"+idCounter;
	}
	String _id;
	
	Object _graphics = null;
	int x,y,w,h;

	Object canvasEl;
	
	public MyGraphics(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		init();
	}
	private void init() {
		canvasEl = DomUtils.createElement("div", DomUtils.getBody());				
		_id= getNewId();
		DomUtils.setAttribute(canvasEl,"id",_id);
		System.out.println(DomUtils.getAttribute(canvasEl, "width"));
		DomUtils.setStyleProp(canvasEl, "position", "absolute");
		DomUtils.setStyleProp(canvasEl, "top", y+"px");
		DomUtils.setStyleProp(canvasEl, "left", x+"px");
		DomUtils.setStyleProp(canvasEl, "width", w+"px");
		DomUtils.setStyleProp(canvasEl, "height", h+"px");
		/**@j2sNative
		 * this._graphics = new ___Graphics(this._id);
		 */{}
	}
	
	
	public void setColor(Color c) {
		String css = c.toCSS();
		/**
		 * @j2sNative
		 * this._graphics.color=css;
		 */{}
	}
	/**
	 * @j2sNative
	 * this._graphics.drawLine(x1,y1,x2,y2);
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {}
	/**
	 * @j2sNative
	 * this._graphics.setPixel(x,y,w,h);
	 */
	public void drawRect(int x, int y, int w, int h) {	}
	/**
	 * @j2sNative
	 * this._graphics.paint();
	 */
	public void paint() {}
	
	/**
	 * @j2sNative
	 * this._graphics.clear();
	 */
	public void clear() {}
}
