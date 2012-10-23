package org.sgx.j2s.model.util;

import java.lang.reflect.Method;

import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.model.ModelElement;
import org.sgx.j2s.model.events.PropertyChangeEvent;
import org.sgx.j2s.util.Collection;
import org.sgx.j2s.util.List;
import org.sgx.j2s.util.Map;
import org.sgx.j2s.util.impl.LWMap;
import org.sgx.j2s.util.impl.Utils;


/**
 * getters / setters / javabeans utilities
 * @author SGURIN
 *
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
		if(JsUtils.isJs()) {
			/**
			 * @j2sNative
			 * this_[pid]=val;
			 */	{}			
			if(this_ instanceof ModelElement && ((ModelElement)this_).isNotifyChangeProperty())
				 ((ModelElement)this_).notifyPropertyChange(
						 new PropertyChangeEvent(((ModelElement)this_), pid, get(this_, pid)));
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
		Map<String, Class> props = new LWMap<String, Class>();
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

public static void test1() {
	System.out.println(Utils.pringMap(getProperties(new ModelHelperTestBean())));
}


public static void main(String[] args) {
	test1();
}



public static Collection<String> getPropertyNames(Object b) {
	return getProperties(b).keys();
}

//	public static Map<String, Class> getProperties(ModelElement b) {
//	Map<String, Class> props = new LWMap<String, Class>();
//	Method[] M = b.getClass().getMethods();
//	for(int i=0; i<M.length; i++) {
//	String name = M[i].getName();
//	if(name.length()>3)
//	if(M[i].getName().startsWith("set")) {
//	String PName = name.substring(3, name.length());
//	for(int j =0; j<M.length; j++) {
//	String name2 = M[j].getName();						
//	if(name2.equals("get"+PName) ) {
//	String propName = name2.substring(3,4).toLowerCase()+name2.substring(4, name2.length());
//	props.put(propName, M[j].getReturnType());
//	}
//	}
//	}
//	}
//	return props;
//	}
}
