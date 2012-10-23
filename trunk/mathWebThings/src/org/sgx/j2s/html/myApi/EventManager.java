package org.sgx.j2s.html.myApi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.events.EventListener;

/**
 * event manager for html elements. 
 * 
 * html elements must have an id. if they don't they will be ided.
 * @author SGURIN
 *
 */
public class EventManager {
	private static EventManager instance;
	public static EventManager getInstance() {
		if (null == instance) {
			instance = new EventManager();
		}
		return instance;
	}
	private static long _idCounter=0;
	private static long _getNextId(){
		_idCounter++;
		return _idCounter;
	}

    

/**	{ elementId -> { eventType -> List<EventListener>} }*/
	Map<String, Map<String,Set<EventListener>>> listeners;
	private EventManager() {
		listeners=new HashMap<String, Map<String,Set<EventListener>>>();
	}	
	
	public void addEventListener(String elemId, String eventType, EventListener l){
		if(!listeners.containsKey(elemId)){
			_registerNativeListener(elemId, eventType);
		}
		_register(elemId, eventType);
		Set<EventListener> listeners2 = listeners.get(elemId).get(eventType);
		if(!listeners2.contains(l)){
			listeners2.add(l);
		}			
	}
	private void _registerNativeListener(String elemId, String eventType) {
		EventManager.getInstance().removeEventListener(elemId, eventType, null);
		Object el = DomUtils.getDocument().getElementById(elemId);

//		  EventWrapper wrapper = new EventWrapper(null); 
		if(el!=null) {
			/**
			 * @j2sNative
  el[eventType]=function(_evt){
//  	var wrapper = new org.sgx.j2s.html.myApi.EventWrapper (_evt);
  	org.sgx.j2s.html.myApi.EventManager.getInstance().dispatchHtmlEvent(_evt);
  }
			 */{}
		}
	}

	public void removeEventListener(String elemId, String eventType, EventListener l){
		_register(elemId, eventType);
		Set<EventListener> listeners2 = listeners.get(elemId).get(eventType);
		if(listeners2.contains(l)){
			listeners2.remove(l);
		}
	}
	public void dispatchHtmlEvent(Object evt_ ) {
		Event evt = (Event)evt_;
		System.out.println(evt.type + " - "+getEventTarget(evt).id);
		
	}
	
	public static Element getEventTarget(Event evt) {
		return evt.target!=null ? evt.target : evt.srcElement;
	}
	
	private void _register(String elemId, String eventType) {		
		if(!listeners.containsKey(elemId)) {
			listeners.put(elemId, new HashMap<String, Set<EventListener>>());
		}
		if(!listeners.get(elemId).containsKey(eventType)){
			listeners.get(elemId).put(eventType, new HashSet<EventListener>());
		}
	}




//	/** The onload event occurs when the user agent finishes loading a window or all frames within a frameset.*/
//	public static final String onload_type = "onload";
//	/** The onunload event occurs when the user agent removes a document from a window or frame.*/
//	public static final String onunload_type = "onunload";
//	/** The onclick event is triggered when the mouse button is clicked over the element.*/
//	public static final String onclick_type = "onclick";
//	/** The ondblclick event occurs when the pointing device button is double clicked over an element.*/
//	public static final String ondblclick_type = "ondblclick";
//	/** The onmousedown event is triggered when the mouse button is pressed over the element.*/
//	public static final String onmousedown_type = "onmousedown";
//	/** The onmouseup event occurs when the pointing device button is released over an element.*/
//	public static final String onmouseup_type = "onmouseup";
//	/** The onmouseover event is triggered when the mouse is moved onto the element.*/
//	public static final String onmouseover_type = "onmouseover";
//	/** The onmousemove event occurs when the pointing device is moved while it is over an element.	     */
//	public static final String onmousemove_type = "onmousemove" ;
//    /**The onmouseout event is triggered when the mouse is moved away from the element.*/
//    public static final String onmouseout_type = "onmouseout" ;
//    /**The onfocus event occurs when an element receives focus either by the pointing device or by tabbing navigation.*/
//    public static final String onfocus_type = "onfocus" ;
//    /**The onblur event is triggered when an element loses focus either by the pointing device or by tabbing navigation.*/
//    public static final String onblur_type = "onblur"; 
//    /**The onkeypress event occurs when a key is pressed and released over an element.*/
//    public static final String onkeypress_type = "onkeypress"; 
//    /**The onkeydown event is triggered when a key is pressed down over the element.*/
//    public static final String onkeydown_type = " onkeydown"; 
//    /** The onkeyup event occurs when a key is released over an element.*/
//    public static final String onkeyup_type = "onkeyup";
//    /** The onsubmit event is triggered when a form is submitted.*/
//    public static final String onsubmit_type = "onsubmit";
//    /** The onreset event occurs when a form is reset.*/
//    public static final String onreset_type = "onsubmit";
//    /** The onselect event is triggered when a user selects some text in a text field.*/
//    public static final String onselect_type = "onselect";
//    
//    /** The onchange event occurs when a control loses the input focus and its value has been modified since gaining focus.*/
//    public static final String onchange_type = "onchange";

	
}
