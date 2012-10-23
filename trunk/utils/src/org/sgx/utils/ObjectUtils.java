package org.sgx.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * here we define data object as simply dictionary with string keys. 
 * we supports data objects in the following formats:
 * 
 * and data object serialization also is provided by this class.
 *  
 * @author sgurin
 *
 */
public class ObjectUtils {

	public static Map<String, Object> transformArrayToMap(Object[][] a) {
		Map<String, Object> m = new HashMap<String, Object>();
		for(int i=0; i<a.length; i++) {
			Object[] a2 = a[i];
			if(a2.length!=2 || !(a2[0]instanceof String) )
				throw new RuntimeException("bad matrix-representing-object format");
			m.put((String)a2[0], a2[1]);
		}
		return m;
	}

	public static Map<String, Object> transformObjectToMap(Object b) throws IllegalArgumentException, JavabeanFormatException, IllegalAccessException, InvocationTargetException {
		Collection<String>pNames = JavaBeansUtils.getProperties(b);
		Map<String, Object> m = new HashMap<String, Object> ();
		for(String name : pNames) {
			m.put(name, JavaBeansUtils.getProperty(b, name));
		}
		return m;
	}
	public static  Map<String, Object> transformObjectToMap_(Object b) {
		try {
			return transformObjectToMap(b);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static Object transformArrToBean(Object[][]a, Class c) throws IllegalArgumentException, JavabeanFormatException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Object b = c.newInstance();
		for(int i=0; i<a.length; i++) {
			Object[] a2 = a[i];
			if(a2.length!=2 || !(a2[0]instanceof String) )
				throw new RuntimeException("bad matrix-representing-object format");
			JavaBeansUtils.setProperty(b, (String)a2[0], a2[1]);
		}
		return b;
	}

	public static Object transformArrToBean_(Object[][] a, Class c) {
		try {
			return transformArrToBean(a,c);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object transformMapToBean(Map<String, Object> data, Class c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, JavabeanFormatException, InvocationTargetException {
		Object o = c.newInstance();
		for(String name : data.keySet()) {
//			try {
			Object v = data.get(name);
			JavaBeansUtils.setProperty(o, name, v);
//			} catch(Exception e) {
//				throw new RuntimeException("Error setting bean property "+name+" to value "+data.get(name));
//			}
		}
		return o;
	}
	public static Object transformMapToBean_(Map<String, Object> data, Class c) {
		try {
			return transformMapToBean(data,c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
