package org.sgx.j2s.yui.widget;

import org.sgx.j2s.model.base.Point;

public class EventUtils {
	/**
	 * @j2sNative
		var targ;
		if (!e) var e = window.event;
		if (e.target) targ = e.target;
		else if (e.srcElement) targ = e.srcElement;
		if (targ.nodeType == 3) // defeat Safari bug
			targ = targ.parentNode;
		return targ;
	 */
	public static Object getTarget(Object e) {return null;}

	public static Point mouseCoords_doc(Object e) {
		int posx=0, posy=0;
		/**
		 * @j2sNative
			if (!e) var e = window.event;
			if (e.pageX || e.pageY) 	{
				posx = e.pageX;
				posy = e.pageY;
			}
			else if (e.clientX || e.clientY) 	{
				posx = e.clientX + document.body.scrollLeft
					+ document.documentElement.scrollLeft;
				posy = e.clientY + document.body.scrollTop
					+ document.documentElement.scrollTop;
			}
		 */{}
		 return new Point(posx, posy);
	}

	public static Point mouseCoords_parent(Object e) {
		int pos_x=0, pos_y=0;
		/**
		 * @j2sNative
	pos_x = event.offsetX?(event.offsetX):event.layerX-document.getElementById(”pointer_div”).offsetLeft;
	pos_y = event.offsetY?(event.offsetY):event.layerY-document.getElementById(”pointer_div”).offsetTop;
		 */{}
		 return new Point(pos_x, pos_y);
	}




}
