package org.sgx.utils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class Utils {
	
	//misc
	public static String []  intToString(int[]i) {
		String [] a = new String [i.length];
		for(int j=0;j<i.length; j++) {
			a[j]=i[j]+"";
		}
		return a;
	}	

	public static String toString(Collection c, String sep) {
		String s = "";
		for(Object o : c) {
			s+=(o+sep);
		}
		return s;
	}
	public static String toString(Collection c) {
		return toString(c,",");
	}
	public static String toString(Map c) {
		String s = "{";
		for(Object o : c.keySet()) {
			s+=(o+"->"+c.get(o)+", ");
		}
		return s+"}";
	}
	public static String toString(Object[] a) {
		String s="[";
		for(int i=0;i<a.length;i++) {
			if(i!=a.length-1)
				s=s+a[i]+",";	
		}
		return s+"]";
	}
	public static String quote(String str) {
		return "\""+str+"\"";
	}
	/**
	 * path in command lines quotation
	 */
public static String quotePath(String path) {
//	if(File.separator.equals("\\"))
		return quote(path);
//	else//unix
//		return path;
	//path.replace(" ", "\\ ")
		
}
	public static String normalizeString(String s) {
		
		return s;
	}
	
	
	//small assertation system for testing
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
			assertsFailed.add(assertMsg);
	}
	
	
	//test
	public static void main(String[] args) {
		int [] b = {1,2,3};
		String[] a = intToString(b);
		if(a.length!=3)
			System.out.println("error 1");
		if(!FSUtils.normalizePath("hola\\").equals("hola"))
			System.out.println("error 2");
		System.out.println("any error?");
		
	}

	public static String toStringF(float[] a) {
		String s="[";
		for(int i=0;i<a.length;i++) {
			if(i!=a.length-1)
				s=s+a[i]+",";	
		}
		return s+"]";
	}

	
	public static Map<Object,Object> toMap2(Object...a) {
		Map<Object,Object> m = new HashMap<Object,Object>();
		for (int i = 0; i < a.length-1; i=i+2) 
			m.put(a[i], a[i+1]);
		return m;
	}
	public static Map<String,String> toMap2(String...a) {
		Map<String,String> m = new HashMap<String,String>();
		for (int i = 0; i < a.length-1; i=i+2) 
			m.put(a[i], a[i+1]);
		return m;
	}
	public static Map<Object,Object> toMap(Object[][] a) {
		Map<Object,Object> m = new HashMap<Object,Object>();
		for (int i = 0; i < a.length; i++) {
			Object[] b = a[i];
			if(b.length<2)
				throw new RuntimeException("incorrect matrix size");
			m.put(b[0], b[1]);
		}
		return m;
	}
	
	public static List toList(Object[] a) {
		List l = new LinkedList();
		for(int i=0; i<a.length; i++) {
			l.add(a[i]);
		}
		return l;
	}
	
	public static Object[] toArray(Collection a) {
		Object [] t = new Object[a.size()];
		Iterator i = a.iterator();int j=0;
		while(i.hasNext()){
			t[j]=i.next();
			j++;
		}
		return t;
	}
	
	public static void foreach(Collection c, Block b) {
		Iterator i = c.iterator();
		while(i.hasNext()) 
			b.eval(i.next());		
	}
	public static Map<Object, Object> transformArrayToMap(Object[][] a) {
		Map<Object, Object> m = new HashMap<Object, Object>();
		for(int i=0; i<a.length; i++) {
			Object[] a2 = a[i];
			if(a2.length!=2 || !(a2[0]instanceof Object) )
				throw new RuntimeException("bad matrix-representing-object format");
			m.put((Object)a2[0], a2[1]);
		}
		return m;
	}

	public static boolean isWindows() {
		return File.separator.equals("\\");
	}
}
