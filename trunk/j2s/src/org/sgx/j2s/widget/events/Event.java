package org.sgx.j2s.widget.events;

import org.sgx.j2s.html.dom.Element;

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
public abstract class Event {
	
	public static final int CLICK_LEFT = 1;
	public static final int CLICK_MIDDLE = 2;
	public static final int CLICK_RIGHT = 3;
	/**
	 * Returns whether or not the "ALT" key was pressed when an event was triggered
	 */
	public boolean altKey;
/**	Returns which mouse button was clicked when an event was triggered */
	public int button;	
	/** RETURNS THE HORIZONTAL COORDINATE OF THE MOUSE POINTER WHEN AN EVENT WAS TRIGGERED */
	public int clientX ;
	/** Returns the vertical coordinate of the mouse pointer when an event was triggered */
	public int clientY 	;
	/**	Returns whether or not the "CTRL" key was pressed when an event was triggered */
	public boolean ctrlKey ;
	/** 	Returns whether or not the "meta" key was pressed when an event was triggered*/
	public boolean metaKey;
/** Returns the element related to the element that triggered the event */
	public Element target;
	/**	Returns the horizontal coordinate of the mouse pointer when an event was triggered */
	public int screenX;
	/** 	Returns the vertical coordinate of the mouse pointer when an event was triggered  */
	public int screenY;
	/**Returns whether or not the "SHIFT" key was pressed when an event was triggered 	*/
	public boolean shiftKey;
}
