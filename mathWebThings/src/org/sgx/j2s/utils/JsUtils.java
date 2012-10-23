package org.sgx.j2s.utils;

import java.util.HashMap;
import java.util.Map;

import org.sgx.j2s.html.myApi.Event;
/**
 * @author sebastian
 *
 */
public class JsUtils {


	public static String[] java2scriptObjectProps = {
		"equals", "hashCode", "getClass", "clone", "finalize", "notify", 
		"notifyAll", "wait"
	};
	
	public static Object getJsEmptyObject() {
		Object o = new Object();
		//i'm not sure so I use 2 techniques...		
		 /**
		  * @j2sNative
		   try {
			   delete o.equals
			   delete o.hashCode
			   delete o.getClass
			   delete o.clone
			   delete o.finalize
			   delete o.notify
			   delete o.notifyAll
			   delete o.wait
		   } catch(e) {}
		  */{ }
		for(int i=0; i<java2scriptObjectProps.length; i++) {
			String propName=java2scriptObjectProps[i];
			 /**
			  * @j2sNative
			   o[propName]=null
			  */{ }
		}
		return o;
	}

	public static void destroyJsObject(Object o, boolean recursive) {
		Object o2=null;
		/**
		 * @j2sNative
		 * for(var i in o) {
		 * if(o[i]!=null) {
		 * 	o2 = o[i];
		 * 	o[i]=null
		 * 	
		 */{}
		 if(recursive)
			 destroyJsObject(o2, true);
		 /**
		  * @j2sNative
		  * }
		  * }
		  */{}
	}
//
//	public static List<ErrorListener> _errHandlers = new LinkedList<ErrorListener>();
//
//	public static void _handleErr(Object msg, Object url, Object l) {
//		 Iterator<ErrorListener> i = _errHandlers.iterator();
//		 ErrorInfo e = new ErrorInfo(msg,url,l);
//		 while(i.hasNext()) {
//			 i.next().notify(e);
//		 }
//	}
//
//	public static void addErrorListener(ErrorListener eh) {		
//		JsUtils.handleErrors(true);
//		_errHandlers.append(eh);
//	}

	public static void handleErrors(boolean b) {
		if(b) {
			/**FIXME: java class name hardcoded in js code... 
			 * @j2sNative
			 onerror=org.sgx.j2s.js.JsUtils._handleErr;
			 */{}
		}
		else {
			/**
			 * @j2sNative
			 onerror=null;
			 */{}
		}
	}

	/**
		 * @j2sNative
	window["j2s.script.debugging"] = true; 
	//ClazzLoader.setLoadingMode ("xhr");
		 */
		public static void activateJ2sDebug() {
			
		}


	/**
	 * @j2sNative
	 * return true; 
	 */
	public static boolean isJs() {
		return false;
	}

	public static Map<String, String> objectToMap(Object o){
		Map<String, String> m = new HashMap<String, String>();
		/**
		 * @j2sNative
		 * for(var i in o)
		 * 	m.put(i,o[i]);
		 */{}
		 return m;
	}
	
	public static String dumpObject(Object o) {
		return Utils.pringMap(objectToMap(o));
	}
	public static String dumpObjectPropsNames(Object o) {
		String s = "[";
		/**
		 * @j2sNative
		 * for(var i in o)
		 * 	s+=i+",";
		 */{}
		 return s+"]";
	}
	/**
	 * @j2sNative
	 * return o[methodName]!=null &&  o[methodName]!=undefined;
	 */
	public static boolean hasMethodNamed(Object o, String methodName) {return false;
	}

	/**
	 * @j2sNative
	 * debugger;
	 */
	public static void firebugDebug() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @j2sNative
	 * 	evt = (evt) ? evt : ((event) ? event : null);
		var KeyID = (window.event) ? window.event.keyCode : evt.keyCode;
		return String.fromCharCode(KeyID);
	 */
	public static String charCodeForKeyEvent(Event evt) {
//		System.out.println(evt.getClass());
		return null;
	}
	
	
	
	
	
	/**
	 * @param arr - an object array representation, like new Object[][]{{"prop1: ", "value1"}} for javascript object {prop1: "value1"}
	 * @return a native javascript object
	 * 
	 * @j2sNative
var obj={};
if(arr==null ||arr==undefined)
	return null;
for(var i = 0; i<arr.length; i++) {
	  obj[arr[i][0]]=arr[i][1]
}
return obj;
	 */
	public static Object buildJsObject(Object[][]arr) {
		return null;
	}
	
	
	
	
	
	//utilities for binding java and javascript functions
	
	
	
	/**
	 * you can bind javascript function properties with public static java methods like, for example
	 *  the following:
	 * 
	 *  bindJsFunction("window.onresize", "org.sgx.j2stests.nativehandler.NativeJavascriptHandler.java_handler");
	 *  
	 *  as you can see, both identificators must be global
	 *  
	 * @param jsfunctionSymbol
	 * @param staticMethodId
	 */
	public static void bindJsFunction(String jsfunctionSymbol, String staticMethodId){
		String s = jsfunctionSymbol+"="+staticMethodId+";";
		
		/**
		 * @j2sNative
		 * eval(s);
		 */{}
	}
	public static Map<String, Runnable> _handlers = new HashMap<String, Runnable>();
	public static void registerHandler(String name, Runnable r) {
		_handlers.put(name, r);
	}
	
}
