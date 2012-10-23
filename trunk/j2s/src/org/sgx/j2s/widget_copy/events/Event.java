package org.sgx.j2s.widget_copy.events;

import org.sgx.j2s.widget_copy.Widget;

/**
 * this object can represent mouse and keyboard events
 * 
 */
public class Event {
	
	//keyboard constants. 
	static int left_arrow = 37;	
	static int up_arrow = 38;	
	static int right_arrow =39;
	static int down_arrow =40;
	static int Insert=45;
	static int Delete = 46;
	static int Home = 36;
	static int End=35;
	static int Page_Up=	33;
	static int Page_Down = 34;
//Function_Keys
	static int F1 = 112;
	static int F12 	=123;
	
	
//	Keyboard Number Keys
	static int KEY_1 =	49;
	static int KEY_2 =	50 	;
	static int KEY_3 =	51 ;
	static int KEY_4= 	52;
	static int KEY_5=	53 ;
	static int KEY_6 =	54 	;
	static int KEY_7 =55 ;
	static int KEY_8 =	56;
	static int KEY_9 =	57 ;
	static int KEY_0 =	48 ;
	
	
	public static final int MOUSE_LEFT = 1;
	public static final int MOUSE_MIDDLE = 2;
	public static final int MOUSE_RIGHT = 3;
	int key;
	
	/**
	 * Returns whether or not the "ALT" key was pressed when an event was triggered
	 */
	public boolean altKey;
/**	Returns which mouse button was clicked when an event was triggered */
	public int button;	
	public int clickCount;
	/**	Returns whether or not the "CTRL" key was pressed when an event was triggered */
	public boolean ctrlKey ;
	/** 	Returns whether or not the "meta" key was pressed when an event was triggered*/
	public boolean metaKey;
/** Returns the widget related to the element that triggered the event */
	Widget target;
	/**Returns whether or not the "SHIFT" key was pressed when an event was triggered 	*/
	public boolean shiftKey;
}
