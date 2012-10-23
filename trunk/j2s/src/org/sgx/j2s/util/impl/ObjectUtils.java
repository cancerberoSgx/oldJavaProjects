package org.sgx.j2s.util.impl;

import org.sgx.j2s.model.util.ModelHelper;
import org.sgx.j2s.util.Collection;
import org.sgx.j2s.util.Iterator;
import org.sgx.j2s.util.Map;

/**
 * here we define data object as simply dictionary with string keys. 
 * we supports data objects in the following formats:
 * 
 * @author sgurin
 *
 */
public class ObjectUtils {

	public static Map<String, Object> transformArrayToMap(Object[][] a) {
		Map<String, Object> m = new LWMap<String, Object>();
		for(int i=0; i<a.length; i++) {
			Object[] a2 = a[i];
			if(a2.length!=2 || !(a2[0]instanceof String) )
				throw new RuntimeException("bad matrix-representing-object format");
			m.put((String)a2[0], a2[1]);
		}
		return m;
	}

	public static Map<String, Object> transformObjectToMap(Object b) {
		Collection<String>pNames = ModelHelper.getPropertyNames(b);
		Map<String, Object> m = new LWMap<String, Object> ();
		Iterator<String> i = pNames.iterator();
		while(i.hasNext()) {
			String name=i.next();
			m.put(name, ModelHelper.get(b, name));
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
	public static Object transformArrToBean(Object[][]a, Class c){
		try {
		Object b = c.newInstance();
		for(int i=0; i<a.length; i++) {
			Object[] a2 = a[i];
			if(a2.length!=2 || !(a2[0]instanceof String) )
				throw new RuntimeException("bad matrix-representing-object format");
			ModelHelper.set(b, (String)a2[0], a2[1]);
		}
		return b;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object transformMapToBean(Map<String, Object> data, Class c) {
		try {
		Object o = c.newInstance();
		Iterator<String> i = data.keys().iterator();
		while(i.hasNext()) {
			String name = i.next();
			Object v = data.get(name);
			ModelHelper.set(o, name, v);
		}
		return o;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
