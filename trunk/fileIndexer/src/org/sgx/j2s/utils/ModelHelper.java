package org.sgx.j2s.utils;

import java.lang.reflect.Method;
import java.util.*;


 

/**
 * getters / setters / javabeans utilities
 * @author SGURIN
 *@j2sRequireImport java.lang.reflect.Modifier
 */
public class ModelHelper {
	/**
	 * @j2sNative
	 * return this_[pid];
	 */
	public static Object get(Object this_, String pid) {		
		//java only code
//		Map<String, Class> props = this_.getProperties();			
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
	public static Map<String, Class> getProperties(Object b) {
		System.out.println(b.getClass().getName());
		Map<String, Class> props = new HashMap<String, Class>();
		Method[] M=null;
		try {
			M = b.getClass().getMethods();
		}catch (Exception e) {
			System.out.println("j2s reflection error for "+b.getClass().getName()+" - "+b);
		}
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

	public static Collection<String> getPropertyNames(Object b) {
		return getProperties(b).keySet();
	}

	public static Map<String, Object> objectToMap(Object obj_) {
		Map<String, Object> m = new HashMap<String, Object>();
		Map<String, Class> props = ModelHelper.getProperties(obj_);
		for (Iterator<String> iterator = props.keySet().iterator(); iterator.hasNext();) {
			String k = iterator.next();
			Object val = ModelHelper.get(obj_, k);
			m.put(k, val);
		}
		return m;
	}
}
