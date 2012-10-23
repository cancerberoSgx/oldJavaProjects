package org.sgx.j2s.yui;

import org.sgx.j2s.html.dom.Element;
import org.sgx.j2s.widget.events.Event;


/**
 * yui static utilities for html event normalization
 * 
 * faltan: 
 * 
 * getXY, getTime, getEvent, generateId, resolveTextNode
 * @author SGURIN
 *
 */
public class YuiEvent {

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
	
	/* OTHERS, TODO:
	DOMActivate
	DOMFocusIn
	DOMFocusOut	
	DOMSubtreeModified 
	DOMNodeInserted 
	DOMNodeRemoved 
	DOMNodeRemovedFromDocument
	DOMNodeInsertedIntoDocument
	DOMAttrModified 
	DOMCharacterDataModified 
	DOMElementNameChanged
	DOMAttributeNameChanged 
	*/

	/**
	 * @param el an html element to assign the listener to
	 * @param sType The type of event to append 
	 * @param l the listener to notify when the event fires 
	 * @param obj An arbitrary object that will be passed as a parameter to the handler
	 * @returns True if the action was successful or defered, false if one or more of the elements could not have the listener attached, or if the operation throws an exception.
	 * 
	 * @j2sNative 
	 * return YAHOO.util.Event.addListener(el, sType, l.run, obj, l)
	 */
	public static boolean addListener(Object el, String sType, 
			EventListener l, Object obj) {
		return false;		
	}
	
	/**Returns the charcode for an event
	 * @j2sNative 
	 * return YAHOO.util.Event.getCharCode(ev);
	 */
	public static int getCharCode(Event ev) {return 0;}
	 
	/**
	 * @return Returns all listeners attached to the given element via addListener
	 * @j2sNative 
	 * return YAHOO.util.Event.getListeners(el);
	 */
	 static Object getListeners (Element el) { return null;}

	/**
	 * @return Returns all listeners attached to the given element via addListener of a specific type of event
	 * @j2sNative 
	 * return YAHOO.util.Event.getListeners(el, sType);
	 */
	 static Object getListeners (Element el, String sType) { return null;}
	 /**
	 * @return Returns the event's pageX
	 * @j2sNative 
	 * return YAHOO.util.Event.getPageX(ev);
	 */
	 public static int getPageX(Event ev) {return 0;}
	 /**
	 * @return Returns the event's pageY
	 * @j2sNative 
	 * return YAHOO.util.Event.getPageY(ev);
	 */	 
	 public static int getPageY(Event ev) {return 0;}
	 /**
	 * @return Returns the event's related target
	 * @j2sNative 
	 * return YAHOO.util.Event.getRelatedTarget(ev);
	 */	 
	 public static Element getRelatedTarget(Event ev) {return null;}	 
	 /**
	 * @return Returns the event's target element. Safari sometimes provides a text node, and this is automatically resolved to the text node's parent so that it behaves like other browsers.
	 * @j2sNative 
	 * return YAHOO.util.Event.getTarget(ev);
	 */	 
	 public static Element getTarget(Event ev) {return null;}
	 
	 /**
	  * Executes the supplied callback when the item with the supplied id is found. This is meant to be used to execute behavior as soon as possible as the page loads. If you use this after the initial page load it will poll for a fixed time for the element. The number of times it will poll and the frequency are configurable. By default it will poll for 10 seconds. The callback is executed with a single parameter: the custom object parameter, if provided.
	  * @param p_id the id of the element to look for.
	  * @param l what to execute when the element is found.
	  * @param p_obj an  object to be passed back as a parameter to l.run
	  * @param checkContent check child node readiness (onContentReady)
	  * @j2sNative 
	  * return YAHOO.util.Event.onAvailable(p_id, l.run, obj, l, checkContent);
	  */
	 public static void onAvailable(String p_id, EventListener l, 
			 Object p_obj, boolean checkContent) {}
	 
	 /**
	  * Executes the supplied callback when the item with the supplied id is found. This is meant to be used to execute behavior as soon as possible as the page loads. If you use this after the initial page load it will poll for a fixed time for the element. The number of times it will poll and the frequency are configurable. By default it will poll for 10 seconds. The callback is executed with a single parameter: the custom object parameter, if provided.
	  * @param p_id an array of ids to look for.
	  * @param l what to execute when the element is found.
	  * @param p_obj an  object to be passed back as a parameter to l.run
	  * @param checkContent check child node readiness (onContentReady)
	  * @j2sNative 
	  * return YAHOO.util.Event.onAvailable(p_id, l.run, obj, l, checkContent);
	  */
	 public static void onAvailable(String [] p_id, EventListener l, 
			 Object p_obj, boolean checkContent) {}
	 
	 /**
	  * Works the same way as onAvailable, but additionally checks the state of sibling elements to determine if the content of the available element is safe to modify. The callback is executed with a single parameter: the custom object parameter, if provided.
	  * @param p_id the id of the element to look for.
	  * @param l what to execute when the element is found.
	  * @param p_obj an  object to be passed back as a parameter to l.run	  * 
	  * @j2sNative 
	  * return YAHOO.util.Event.onContentReady(p_id, l.run, obj, l);
	  */
	 public static void onContentReady(String p_id, EventListener l, 
			 Object p_obj, boolean checkContent) {}
	 
	 /**
	  * Works the same way as onAvailable, but additionally checks the state of sibling elements to determine if the content of the available element is safe to modify. The callback is executed with a single parameter: the custom object parameter, if provided.
	  * @param p_id an array of ids to look for.
	  * @param l what to execute when the element is found.
	  * @param p_obj an  object to be passed back as a parameter to l.run	  * 
	  * @j2sNative 
	  * return YAHOO.util.Event.onContentReady(p_id, l.run, obj, l);
	  */
	 public static void onContentReady(String [] p_id, EventListener l, 
			 Object p_obj, boolean checkContent) {}
	 
	 /**
	  * Executes the supplied callback when the DOM is first usable. This will execute immediately 
	  * if called after the DOMReady event has fired. @todo the DOMContentReady event does not 
	  * fire when the script is dynamically injected into the page. This means the DOMReady 
	  * custom event will never fire in FireFox or Opera when the library is injected. 
	  * It _will_ fire in Safari, and the IE implementation would allow for us to fire it if 
	  * the defered script is not available. We want this to behave the same in all browsers. 
	  * Is there a way to identify when the script has been injected instead of included inline? 
	  * Is there a way to know whether the window onload event has fired without having had a 
	  * listener attached to it when it did so?
		The callback is a CustomEvent, so the signature is:
		type <string>, args <array>, customobject <object>
		For DOMReady events, there are no fire argments, so the signature is:
		"DOMReady", [], obj
	  * @param l
	  * @param p_obj
	  * @j2sNative 
	  * return YAHOO.util.Event.onDOMReady(l.run, obj);
	  */
	 public static void onDOMReady(EventListener l, Object obj) {}
	 /**
	  * Prevents the default behavior of the event
	  * @j2sNative 
	  * return YAHOO.util.Event.preventDefault(ev);
	  */
	 public static void preventDefault(Event ev ){}
	 /**
	  * Removes all listeners attached to the given element via addListener. Optionally, the node's children can also be purged. Optionally, you can specify a specific type of event to remove. 
	  * @param recurse recursively purge this element's children as well. Use with caution. 
	  * @param sType type of listener to purge. if null all listeners will be removed
	  * @j2sNative 
	  * if(sType!=null)
	  * 	YAHOO.util.Event.purgeElement(el, recurse, sType)
	  * else
	  * 	YAHOO.util.Event.purgeElement(el, recurse)
	  */
	 public static void purgeElement(Element el, boolean recurse, String sType){}
	 /**
	  * Removes an event listener
	  * if l == null then all event handlers for the type of event are removed.
	  * @return true if the unbind was successful, false otherwise.
	  * @j2sNative 
	  * if(l!=null)
	  * 	YAHOO.util.Event.removeListener(el, sType, l.run)
	  * else
	  * 	YAHOO.util.Event.removeListener(el, sType)
	  */
	 public static boolean removeListener(Element el, String sType, EventListener l){return false;}
	 
	 /**
	  * Convenience method for stopPropagation + preventDefault
	  * @j2sNative 
	  * return YAHOO.util.Event.stopEvent(ev);
	  */
	 public  static void stopEvent  (Event ev ){}
	 
	 /**
	  * Stops event propagation
	  * @j2sNative 
	  * return YAHOO.util.Event.stopPropagation(ev);
	  */
	 public  static void stopPropagation  (Event ev ){}
	 

}
