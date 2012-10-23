package org.sgx.j2s.raphael;

import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.Rectangle;

/**
 * 
 * @author SGURIN
 *
 */
public class Element {
   	
	/**
	 * reference to native raphael element. don't rename me!
	 */
	private Object _data;
	
public Element(Object _data) {
		super();
		this._data = _data;
	}
public Object getDomElement() {
	return null;
}
/**
 * Rotates element by given degree around its center.
 * @j2sNative
 * this._data.rotate(degrees);
 */
public void rotate(int degrees) {
	
}
/**
 * Moves element around the canvas by given dimensions.
 * @param dx
 * @param dy
 */
public void translate(int dx, int dy)  {
	
}
/**
 *  Performs matrix transformation on element.
Parameters

example: reflects circle down    c.matrix(1, 0, 0, -1, 0, 0);

   1. xx number
   2. xy number
   3. yx number
   4. yy number
   5. dx number
   6. dy number

 */
public void matrix(int xx, int xy, int yx, int yy, int dx, int dy) {
	
}
/**
 * 

Scales element by given amount of times. 
 * @param xTimes
 * @param yTimes
 */
public void scale(double xTimes, double yTimes) {
	
}

/**
 * @j2sNative
 * return this._data.getBBox();
 */
public Rectangle getBounds() {
	return null;	
}

/**
 * @j2sNative
 * this._data.toFront();
 * return this;
 */
public Element toFront() {return null;}
//attr names:
//*  cx
//* cy
//* dasharray
//* fill-opacity
//* fill
//* font-family
//* font-size
//* font-weight
//* font
//* gradient
//* height
//* joinstyle
//* opacity
//* r
//* rx
//* ry
//* stroke-opacity
//* stroke-width
//* stroke
//* width
//* x
//* y
public static final String ATTR_CX = "cx";
public static final String ATTR_CY = "cy";
public static final String ATTR_DASHARRAY = "dasharray";
public static final String ATTR_FILL_OPACITY = "fill-opacity";
public static final String ATTR_FILL = "fill";
public static final String ATTR_FONT_FAMILY = "font-family";
//TODO: OTHERS
/**
 * @j2sNative
 *  this._data.attr(name);
 *  return this;
 */
public Object getAttr(String name) {
	return null;
	
}
/**
 * @j2sNative
 *  this._data.attr(name, value);
 *  return this;
 */
public void setAttr(String name, Object value) {
	
}


public Element setAttrs(Object[][] attrs) {
	Object o = Utils.toJavascript(attrs);
	/**
	 * @j2sNative
	 * this._data.attr(o);
	 */{}
	 return this;
}

/**
 * @j2sNative
 * return this._data.show();
 */
public void show(){
	
}
/**
 * @j2sNative
 * return this._data.hide();
 */
public void hide(){
	
}

}
