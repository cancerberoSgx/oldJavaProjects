package org.sgx.j2s.widget_copy.events;

public class EventHtmlUtil {

	/**
	 * a method for getting the correct buttom id based on Event constants from  ahtml key event
from http://unixpapa.com/js/mouse.html;
	 */
	public static int mouseEventGetButton(Object event) {
		Integer ewhich = 0, ebutton = 0;
		/** @j2sNative
		 ewhich = event.which;
		 eutton = event.button;
		 */{}
		 int button;
		 if(ewhich==null){ //IE
			 button= (ebutton < 2) ? Event.MOUSE_LEFT :
				 ((ebutton == 4) ? Event.MOUSE_MIDDLE : Event.MOUSE_RIGHT);
		 }
		 else { // All others 
			 button= (ewhich < 2) ? Event.MOUSE_LEFT :
				 ((ewhich == 2) ? Event.MOUSE_MIDDLE : Event.MOUSE_RIGHT);
		 }
		 return button;
	}
	
	/**
	 * a method for getting the correct key code (based on Event constants) from a html's mouseevent .
	 */
	public static char mouseEventGetKey(Object event) {
		char c = 0;
		/**
		 * @j2sNative
if (event.which == null
     c= String.fromCharCode(event.keyCode);    // IE
else if (event.which > 0)
     c= String.fromCharCode(event.which);	  // All others
else
	//ignore special keys
		 */{}
		 return c; 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
