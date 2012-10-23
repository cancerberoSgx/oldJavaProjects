package org.sgx.j2s.widget.events;

import org.eclipse.swt.events.KeyEvent;
import org.sgx.j2s.widget.IWidget;

//import org.sgx.j2s.xml.dom.Element;

/*
 * Copyright (c) 2000 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * See W3C License http://www.w3.org/Consortium/Legal/ for more details.
 */

/**
 * sgurin has modified this class so only html dom event object compatible attributes are supported. see:
 * http://www.w3schools.com/htmldom/dom_obj_event.asp
 * 
 */
public class UIEvent extends Event{
	public static final int ONMOUSEMOVE_TYPE=21;
	public static final int ONCLICK_TYPE=22;
	public static final int ONMOUSEDOWN_TYPE=24;
	public static final int ONMOUSEUP_TYPE=25;
	public static final int ONMOUSEOVER_TYPE=26;
	public static final int ONMOUSEOUT_TYPE=27;
	public static final int ONMOUSEIN_TYPE = 28;
	public static final int ONKEYPRESSED_TYPE = 41;
	public static final int ONKEYRELEASE_TYPE = 42;
	
	public static int[] getUIEventTypes(){return new int[]{
		ONMOUSEMOVE_TYPE,	ONCLICK_TYPE, 
		ONMOUSEDOWN_TYPE, ONMOUSEUP_TYPE, ONMOUSEOVER_TYPE, 
		ONMOUSEOUT_TYPE, ONMOUSEIN_TYPE, ONKEYPRESSED_TYPE ,
		ONKEYRELEASE_TYPE
	};};
	
	public static final int BUTTON_LEFT = 1;
	public static final int CLICK_MIDDLE = 2;
	public static final int CLICK_RIGHT = 3;
	
//	static {
//		Event.addEventTypes(getUIEventTypes());
//	}
	
	public int count;
	/**
	 * Returns whether or not the "ALT" key was pressed when an event was triggered
	 */
	public boolean altKey;
/**	Returns which mouse button was clicked when an event was triggered */
	public int button;	
	/** RETURNS THE HORIZONTAL COORDINATE OF THE MOUSE POINTER WHEN AN EVENT WAS TRIGGERED, relative to target bounds */
	public int y ;
	/** Returns the vertical coordinate of the mouse pointer when an event was triggered, relative to target bounds  */
	public int x;
	
	/**	Returns whether or not the "CTRL" key was pressed when an event was triggered */
	public boolean ctrlKey ;
	/** 	Returns whether or not the "meta" key was pressed when an event was triggered*/
	public boolean metaKey;
/** Returns the element related to the element that triggered the event */
	public IWidget target;
	/**	Returns the horizontal coordinate of the mouse pointer when an event was triggered */
//	public int screenX;
//	/** 	Returns the vertical coordinate of the mouse pointer when an event was triggered  */
//	public int screenY;
//	/**Returns whether or not the "SHIFT" key was pressed when an event was triggered 	*/
	public boolean shiftKey;
	public String character;
}
