package org.sgx.j2s.html.myApi;

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
	
	/**  	Loading of an image is interrupted */
	public static final String onabort="onabort";
	/**An element loses focus */
	public static final String onblur="onblur"; 	
	/**The content of a field changes 	*/
	public static final String onchange="onchange"; 	
	/**Mouse clicks an object */
	public static final String onclick ="onclick";	
	/**Mouse double-clicks an object */
	public static final String ondblclick ="ondblclick";	
	/**An error occurs when loading a document or an image*/
	public static final String onerror="onerror"; 	
	/** 	An element gets focus */
	public static final String onfocus="onfocus"; 
	/**A keyboard key is pressed */
	public static final String onkeydown ="onkeydown";		
	/**A keyboard key is pressed or held down 	*/
	public static final String onkeypress ="onkeypress";
	/**A keyboard key is released*/
	public static final String onkeyup="onkeyup"; 	 	
	/**A page or an image is finished loading*/
	public static final String onload ="onload";	 	
	/**A mouse button is pressed*/
	public static final String onmousedown ="onmousedown";	 
	/**The mouse is moved*/
	public static final String onmousemove ="onmousemove";	 
	/**The mouse is moved off an element */
	public static final String onmouseout ="onmouseout";	
	/**The mouse is moved over an element 	*/
	public static final String onmouseover ="onmouseover";	
	/**A mouse button is released*/ 
	public static final String onmouseup="onmouseup"; 	
	/**The reset button is clicked*/
	public static final String onreset ="onreset";	
	/**A window or frame is resized */
	public static final String onresize ="onresize";	
	/**Text is selected 	*/
	public static final String onselect ="onselect";	
	/**	The submit button is clicked 	*/
	public static final String onsubmit ="onsubmit";
	/**	The user exits the page*/
	public static final String onunload ="onunload";
	
	
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
	public Element srcElement;
	
	
	/**	Returns the horizontal coordinate of the mouse pointer when an event was triggered */
	public int screenX;
	/** 	Returns the vertical coordinate of the mouse pointer when an event was triggered  */
	public int screenY;
	/**Returns whether or not the "SHIFT" key was pressed when an event was triggered 	*/
	public boolean shiftKey;
	/**Returns the name of the event */
	
	public String type;
}
