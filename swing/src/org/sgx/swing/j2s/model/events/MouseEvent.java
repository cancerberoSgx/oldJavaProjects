package org.sgx.swing.j2s.model.events;

/**
 * en js soy un obejto html dom nativo. 
 * 
 * @author sg
 *
 */
public class MouseEvent {
	/* evet type constants. taken from http://www.w3.org/TR/DOM-Level-3-Events/events.html#Event-types */
	public static final String TYPE_FOCUS = "focus";
	public static final String TYPE_BLUR = "blur";	
	public static final String TYPE_TEXTINPUT = "textInput";
	
	public static final String TYPE_CLICK = "click";
	public static final String TYPE_DBLCLICK = "dblclick";
	public static final String TYPE_MOUSEDOWN = "mousedown";
	public static final String TYPE_MOUSEUP = "mouseup";
	public static final String TYPE_MOUSEOVER = "mouseover";
	public static final String TYPE_MOUSEMOVE = "mousemove";
	public static final String TYPE_MOUSEOUT = "mouseout";	

	public static final String TYPE_MOUSEMULTIWHEEL = "mousemultiwheel";
	public static final String TYPE_MOUSEWHEEL = "mousewheel";
	
	public static final String TYPE_KEYDOWN = "keydown";
	public static final String TYPE_KEYUP = "keyup";
	
	public static final String TYPE_LOAD = "load";
	public static final String TYPE_UNLOAD = "unload";
	public static final String TYPE_ABORT = "abort";
	public static final String TYPE_ERROR = "error";
	public static final String TYPE_SELECT = "select";
	public static final String TYPE_CHANGE = "change";
	public static final String TYPE_SUBMIT = "submit";
	public static final String TYPE_RESET = "reset";
	public static final String TYPE_RESIZE = "resize";
	public static final String TYPE_SCROLL = "scroll";
}
