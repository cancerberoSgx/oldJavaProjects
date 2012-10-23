package org.sgx.j2s.js;

import org.sgx.j2s.util.Iterator;
import org.sgx.j2s.util.List;
import org.sgx.j2s.util.Map;
import org.sgx.j2s.util.impl.ErrorInfo;
import org.sgx.j2s.util.impl.ErrorListener;
import org.sgx.j2s.util.impl.LWMap;
import org.sgx.j2s.util.impl.LinkedList;
import org.sgx.j2s.util.impl.Utils;

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

	public static List<ErrorListener> _errHandlers = new LinkedList<ErrorListener>();

	public static void _handleErr(Object msg, Object url, Object l) {
		 Iterator<ErrorListener> i = _errHandlers.iterator();
		 ErrorInfo e = new ErrorInfo(msg,url,l);
		 while(i.hasNext()) {
			 i.next().notify(e);
		 }
	}

	public static void addErrorListener(ErrorListener eh) {		
		JsUtils.handleErrors(true);
		_errHandlers.append(eh);
	}

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
	ClazzLoader.setLoadingMode ("xhr");
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
		Map<String, String> m = new LWMap<String, String>();
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
}
