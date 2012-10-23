package org.sgx.j2s.yui;

import org.sgx.j2s.html.myApi.DomUtils;

public class ScriptLoader {

	/**
	 * @j2sNative
try {
	if(YAHOO.lang.isBoolean(false))
		return true;
	else
		return false;
} catch(e) {
	return false;
}
	
	 */
	public boolean isYuiLoaded() {return false;}
	
	public void load_yahoo_dom_events() {
		if(!isYuiLoaded())
			DomUtils.loadScript("yahoo-dom-event.js");
	}
	public void load_yahoo_dom_events_element() {
		if(!isYuiLoaded())
			DomUtils.loadScript("yahoo-dom-event-element.js");
	}
	
	public static void testYahoo() {	
		DomUtils.loadScript("yahoo-dom-event.js");
		/**@j2sNative
		if(YAHOO.lang.isBoolean(false))
			alert("Congratulations!,yui is loaded");
		 */{}
		System.out.println("end");
	}
	
	
	public static void testExt() {	
		DomUtils.loadScript("ext.js");
		/**@j2sNative
		 * Ext.onReady(function() {
				alert("Congratulations!  You have Ext configured correctly!");
			});
		 */{}
		System.out.println("end");
	}
	
	public static void main(String[] args) {
		testYahoo();
	}
}
