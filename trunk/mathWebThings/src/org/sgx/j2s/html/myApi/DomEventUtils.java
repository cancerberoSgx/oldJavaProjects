package org.sgx.j2s.html.myApi;

public class DomEventUtils {
	
	
//	public static void registerEventListener(ElementWrapper elementWrapper, String eventType, 
//			EventListener l){
//		String methodName="dispatch_"+eventType.toLowerCase();
//		Object element = elementWrapper.getElement();
//		/**
//		 * @j2sNative
//		 * element[eventType]=l[methodName];
//		 */{}
//	}
//
//	public static void registerEventListener(ElementWrapper element, String[] eventNames,
//			EventListener eventListener) {
//		for (int i = 0; i < eventNames.length; i++) {
//			registerEventListener(element, eventNames[i], eventListener);
//		}
//	}
	
	public static Object listeners=null;
//	/**
//	 * @j2sNative
//		org.sgx.j2s.html.myApi.DomEventUtils.listeners={};
//	 */
//	static{
//		for (int i = 0; i < array.length; i++) {
//			
//		}
//		element[eventType]=l[methodName];
//		
//	}
	
	public static void registerEventListener(ElementWrapper elementWrapper, String eventType, 
			EventListener l){
		String methodName="dispatch_"+eventType.toLowerCase();
		Object element = elementWrapper.getElement();
		String s = "eval(\\\"" +
				"\\\");";
		/**
		 * @j2sNative
		 * org.sgx.j2s.html.myApi.DomEventUtils.listeners[element]=s;
		
		 * 
		 */{}
	}

	public static void registerEventListener(ElementWrapper element, String[] eventNames,
			EventListener eventListener) {
		for (int i = 0; i < eventNames.length; i++) {
			registerEventListener(element, eventNames[i], eventListener);
		}
	}

	public static String printEvent( Event e) {
		String s="Event(";
		s+="altKey="+e.altKey+", "+
			"ctrlKey="+e.ctrlKey+", "+
			"shiftKey="+e.shiftKey+", "+
			"button="+e.button+", "+
			"clientX="+e.clientX+", "+
			"clientY="+e.clientY+", "+
			"screenX="+e.screenX+", "+
			"screenY="+e.screenY+", ";
		return s+")";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
