package org.sgx.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class JavaUtils {

	//	collection utils
	public static Collection select(Collection c, Predicate p) {
		List l = new LinkedList();
		for(Object o : c) 
			if(p.select(o))
				l.add(o);
		return l;
	}	
	public static Collection select(Object[] c, Predicate p) {
		List l = new LinkedList();
		for(int i=0; i<c.length; i++) 
			if(p.select(c[i]))
				l.add(c[i]);
		return l;
	}
	public static Object selectFirst(Object[] c, Predicate p) {
		for(int i=0; i<c.length; i++) 
			if(p.select(c[i]))
				return c[i];
		return null;
	}
	public static boolean exists(Collection c, Predicate p) {
		for(Object o : c) 
			if(p.select(o))
				return true;
		return false;
	}
	public static void foreach(Collection c, Block b) {
		for(Object o : c) {
			b.eval(o);
		}
	}
	//javabeans helpers
	
	public static void assertTrue(boolean b, String msj) {
		if(!b)
			System.out.println("ASSERTATION FAILED : "+msj);
	}
	
	
	public static String matchOrDie(String[] a, StringMatcher m, String ex_msg) {
		String mt = match(a,m);
		if(mt==null)
			throw new RuntimeException(ex_msg);
		else return mt;
	}
	public static  String match(String[] a, StringMatcher m) {
		for(int i=0;i<a.length; i++)
			if(m.match(a[i]))
				return a[i];
		return null;
	}

	public static List toList(Object[]a) {
		List l = new LinkedList();
		for(int i=0; i<a.length; i++) {
			l.add(a[i]);
		}
		return l;
	}
	public static Object[]subarray(Object[] ar, int[]inds) {
		Object[]a=new Object[inds.length];
		for(int i=0; i<inds.length; i++) {
			a[i]=ar[inds[i]];
		}
		return a;
	}


	//misc utilities	
	public static boolean notNull(String s) {
		return s!=null && !s.equals("");
	}

	public int getInt(String s, int def) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return def;
		}
	}
	
	
	
	
	
	//file system utilities
	public String getFileExtension(File f) {
		if(f.isDirectory())
			return "";
		return getFileExtension(f.getName());
	}
	public String getFileExtension(String name) {
		int i = name.lastIndexOf('.');
		if(i!=-1)
			return name.substring(i, name.length());
		else
			return "";
	}
	public String getFileName(String absPath) {
		if(absPath==null||absPath.equals(""))
			return null;
		String[] parts = splitFoldersInName(absPath);
		if(parts==null||parts.length==0)
			return null;
		else return parts[parts.length-1];
	}
	public String[] splitFoldersInName(String absPath) {
		boolean isUnixPath=absPath.startsWith("/");
		String[] r;
		if(isUnixPath) {
			r = absPath.split("/");
			if(r.length>0) {
				r[0]="/";
			}
			else //it means that absPath=="/"
				r=new String[]{"/"};
		}
		else {
			r = absPath.split("\\\\");
		}		
		return r;
	}
	public String[] splitFoldersAbsolutePaths(String absPath) {
		String separator = absPath.startsWith("/") ? "/" : "\\";
		String[] names=splitFoldersInName(absPath);
		String[] paths = new String[names.length];
		for (int i = 0; i < paths.length; i++) {
			String s = "";
			for (int j = 0; j <= i; j++) {
				if(!(i>0&&j==0)) 
					s+=names[j];
				if(j<i)
					s+=separator;
			}
			paths[i]=s;			
		}
		
		return paths;
	}
	
	
	
	//date utilities
	private static Locale dateFormatLocale;
	/**
	 * return the Locale used for date formatting. It is always en-US
	 */
	private static Locale getLocale() {
		if(dateFormatLocale==null)
			dateFormatLocale=new Locale("en","US");
		return dateFormatLocale;
	}	
	private static SimpleDateFormat dateFormatDateTime;	
	private static final String 
		dateFormatPatternDateTime="yyyy-MM-dd H:mm:ss:SS";

	private static SimpleDateFormat getDateFormatDateTime() {
		if(dateFormatDateTime==null)
			dateFormatDateTime=new SimpleDateFormat(dateFormatPatternDateTime, getLocale());
		return dateFormatDateTime;
	}
	public String formatDate(Date d) {
		return getDateFormatDateTime().format(d);
	}
	
	
	
	//collection utilities
	public static<T> Collection<T> select(Collection<T> c, Selector<T> selector) {
		List<T> l = new LinkedList<T>();
		for(T t : c) {
			if(selector.select(t))
				l.add(t);
		}
		return l;
	}
	
	public static <T> Collection<T> select(T[] c, Selector<T> p) {
		List<T> l = new LinkedList<T>();
		for(int i=0; i<c.length; i++) 
			if(p.select(c[i]))
				l.add(c[i]);
		return l;
	}
	public static boolean exists(Collection c, Selector p) {
		for(Object o : c) 
			if(p.select(o))
				return true;
		return false;
	}

	
	
	
	
//	//debug utilities
//	public String print(List<Document>l) {
//		String s = "";
//		for(Document d : l) {
//			s+=d.get(FIELD_FILE_NAME)+"\t"+d.get(FIELD_MEDIA_NAME)+"\t"+
//				d.get(FIELD_PACKAGE_NAME)+"\n";
//		}
//		return s;
//	}

	
	public static String toString(Collection c) {
		String s = c.getClass()+"{";
		for(Object o : c)
			s+=o+",";
		return s+"}";
	}

	public String toString(Object[] a) {
		if(a==null)
			return "null";
		String s = "[";
		for (int i = 0; i < a.length; i++) {
			s+=a[i]+",";
		}
		return s;
	}


	
	public static Map<Object,Object> toMap(Object...a) {
		Map<Object,Object> m = new HashMap<Object,Object>();
		for (int i = 0; i < a.length-1; i=i+2) 
			m.put(a[i], a[i+1]);
		return m;
	}
}
