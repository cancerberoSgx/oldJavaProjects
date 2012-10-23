package org.sgx.j2s.util.impl;


import org.sgx.j2s.util.Block;
import org.sgx.j2s.util.Collection;
import org.sgx.j2s.util.Iterator;
import org.sgx.j2s.util.List;
import org.sgx.j2s.util.Map;
import org.sgx.j2s.util.Predicate;
import org.sgx.j2s.util.impl.*;


public class Utils<T> {
	private static long count = 0;
	public static long getUnique() {
		count++;
		return count-1;
	}
	public static Map<Object, Object> transformArrayToMap(Object[][] a) {
		Map<Object, Object> m = new LWMap<Object, Object>();
		for(int i=0; i<a.length; i++) {
			Object[] a2 = a[i];
			if(a2.length!=2 || !(a2[0]instanceof Object) )
				throw new RuntimeException("bad matrix-representing-object format");
			m.put((Object)a2[0], a2[1]);
		}
		return m;
	}
	
	public static java.util.Map toJMap(org.sgx.j2s.util.Map m) {
		if(m instanceof LWMap)
			return (java.util.Map) ((LWMap)m)._map;
		java.util.Map jm = new java.util.HashMap();
		Iterator i = m.keys().iterator();
		while(i.hasNext()){
			Object k = i.next();
			jm.put(k, m.get(k));
		}
		return jm;
	}
	/**
	 * @return a java, javascrpipt, json array compatible repr.
	 */
	public static String toString(Object[] a) {
		String s="[";
		for(int i=0;i<a.length;i++) {
			if(i!=a.length-1)
				s=s+a[i]+",";	
		}
		return s+"]";
	}
	
	public static List<String> assertsFailed = new LinkedList<String>();
	public static void cleanTests() {
		assertsFailed.clear();
	}
	public static String printAssertsFailed() {
		Iterator<String> i = assertsFailed.iterator();
		String s=(assertsFailed.isEmpty()?"Non failed asserts":"Asserts failed: ");
		while(i.hasNext()) {
			s+="\n"+i.next();
		}
		return s;
	}
	public static void assertTrue(boolean b, String assertMsg) {
		if(!b)
			assertsFailed.append(assertMsg);
	}

	public static List toList(Object[] a) {
		List l = new LinkedList();
		for(int i=0; i<a.length; i++) {
			l.append(a[i]);
		}
		return l;
	}
	public List<T> toList_(T[] a) {
		return toList(a);
	}
	public static Object[] toArray(List a) {
		Object [] t = new Object[a.size()];
		Iterator i = a.iterator();int j=0;
		while(i.hasNext()){
			t[j]=i.next();
			j++;
		}
		return t;
	}
	public T[] toArray_(List<T> a) {
		return (T[]) toArray(a);
	}
	
	

	//	collection utils
	public static Collection select(Collection c, Predicate p) {
		List l = new LinkedList();
		Iterator i = c.iterator();
		while(i.hasNext()) {
			Object o = i.next();
			if(p.select(o))
				l.append(o);
		}
		return l;
	}	
	public static Collection select(Object[] c, Predicate p) {
		List l = new LinkedList();
		for(int i=0; i<c.length; i++) 
			if(p.select(c[i]))
				l.append(c[i]);
		return l;
	}
	public static Object selectFirst(Object[] c, Predicate p) {
		for(int i=0; i<c.length; i++) 
			if(p.select(c[i]))
				return c[i];
		return null;
	}
	public static boolean exists(Collection c, Predicate p) {
		Iterator i = c.iterator();
		while(i.hasNext()) {
			Object o = i.next();
			if(p.select(o))
				return true;
		}
		return false;
	}
	public static void foreach(Collection c, Block b) {
		Iterator i = c.iterator();
		while(i.hasNext()) 
			b.eval(i.next());		
	}
		
	public static Object[]subarray(Object[] ar, int[]inds) {
		Object[]a=new Object[inds.length];
		for(int i=0; i<inds.length; i++) {
			a[i]=ar[inds[i]];
		}
		return a;
	}
	public static String pringMap(Map objectToMap) {	
		Iterator i = objectToMap.keys().iterator();
		String s = "[";
		while(i.hasNext()) {
			Object k = i.next();
			s=s+k+":\n"+objectToMap.get(k);
			if(i.hasNext())
				s=s+"\n\n";
		}
		return s+"]";
	}
	
	public static String quote(String absolutePath) {
		return "\""+absolutePath+"\"";
	}
	
	
	
}
