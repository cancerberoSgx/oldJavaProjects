package org.stringtree.json;

import java.lang.reflect.Method;
import java.util.*;


/**
 * getters / setters / javabeans utilities
 * @author SGURIN
 *
 */
public class ReflectionHelper {
	/**
	 * @j2sNative
	 * return this_[pid];
	 */
	public static Object get(Object this_, String pid) {
		String s = "get"+pid.substring(0,1).toUpperCase()+pid.substring(1, pid.length());		
		try {
			java.lang.reflect.Method m = this_.getClass().getMethod(s, null);
			return m.invoke(this_);
		} catch (Exception e) {
			String s2 = "is"+pid.substring(0,1).toUpperCase()+pid.substring(1, pid.length());		
			try {
				java.lang.reflect.Method m = this_.getClass().getMethod(s2, null);
				return m.invoke(this_);
			} catch (Exception e2) {

				throw new RuntimeException("error getting property "+pid+ 
						" (object must implement method "+s+"(void) or an exception in method call occurs) - "+e2.getLocalizedMessage());
			}
		}
	}



	public static void set(Object this_, String pid, Object val) {
		if(isJs()) {
			/**
			 * @j2sNative
			 * this_[pid]=val;
			 */	{}
		}
		else {
			Map<String, Class> props = getProperties(this_);			
			String s = "set"+pid.substring(0,1).toUpperCase()+pid.substring(1, pid.length());		
			try {
				java.lang.reflect.Method m = this_.getClass().getMethod(s, props.get(pid));
				m.invoke(this_, val);
			} catch (Exception e) {
				throw new RuntimeException("error setting property "+pid+ 
						" (object must implement method "+s+"("+props.get(pid)+") or an exception in method call occurs)");
			}			
		}				
	}
	public static Map<String, Class> getProperties(Object b) {
		System.out.println(b.getClass().getName());
		Map<String, Class> props = new HashMap<String, Class>();
		Method[] M = b.getClass().getMethods();
		for(int i=0; i<M.length; i++) {
			String name = M[i].getName();
			if(name.length()>3)
				if(M[i].getName().startsWith("set")) {
					String PName = name.substring(3, name.length());
					for(int j =0; j<M.length; j++) {
						String name2 = M[j].getName();			

						if(name2.equals("get"+PName) ) {
							String propName = name2.substring(3,4).toLowerCase()+name2.substring(4, name2.length());
							props.put(propName, M[j].getReturnType());
						}
						else if(name2.equals("is"+PName) ) {
							String propName = name2.substring(2,3).toLowerCase()+name2.substring(3, name2.length());
							props.put(propName, M[j].getReturnType());
						}
					}
				}
		}
		return props;
	}
	
	public static  boolean isArray(Object o) {
		if(isJs())
			return (o instanceof Object[]);
		else
			return o.getClass().isArray();
	}

	public static boolean characterIsIsoControl(char c) {
		int codePoint = (int)c;
		return (codePoint >= 0x0000 && codePoint <= 0x001F) || 
			(codePoint >= 0x007F && codePoint <= 0x009F);

	}
	public static Object arrayGet(Object o, int i) {
		if(isArray(o)) {
			/**
			 * @j2sNative
			 * return o[i];
			 */{
				 return java.lang.reflect.Array.get(o, i);
			 }
		}
		else
			return 0;
	}


	public static Collection<String> getPropertyNames(Object b) {
		return getProperties(b).keySet();
	}


	/**
	 * @j2sNative
	 * return true; 
	 */
	public static boolean isJs() {
		return false;
	}


	public static int arrayLength(Object o) {
		if(isArray(o)) {
			/**
			 * @j2sNative
			 * return o.length;
			 */{
				 return java.lang.reflect.Array.getLength(o);
			 }
		}
		else
			return 0;
		
	}

}
