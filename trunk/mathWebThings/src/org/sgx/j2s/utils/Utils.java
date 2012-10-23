package org.sgx.j2s.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.sgx.j2s.widget.base.Rectangle;

 
/**
 * utils needed for raphael. this functions are from a large Util class, 
 * but we extracte them here to isolate this package
 * 
 * @author SGURIN
 *
 */
public class Utils {
	static Utils instance;
	public static Utils getInstance() {
		if(instance==null) {
			instance=new Utils();
		}
		return instance;
	}
	private Utils() {}
	
	public Object cleanJsObject() {
		Object o = null;
		/**
	 @j2sNative
	 function __Obj__() {}
	 var extensions = ["equals", "hashCode", "getClass", "clone", "finalize", "notify","notifyAll", "wait"]
	 for(var i=0; i<extensions.length; i++)
	 		__Obj__.prototype[extensions[i]]=null;
	 o=new __Obj__();
		 */{}	
		 return o;
	}
	
	LWMap<Integer, Runnable> _runnables = new LWMap<Integer, Runnable>();
	public void runRunnable(Integer k) {
		Runnable r = _runnables.get(k);
		r.run();
	}
	int _runnableCount = 0;
	public void installTimer(Runnable r, long ms) {
		_runnableCount++;
		_runnables.put(_runnableCount, r);
		String thisClassName = Utils.class.getName();
		String js_stmt = thisClassName+".getInstance().runRunnable("+_runnableCount+")";
		/**
		 * @j2sNative
		 *	setTimeout(js_stmt, ms);
		 */{
			 java.util.Timer timer = new java.util.Timer();
			 timer.schedule(new InternalTimerTask(r), ms);
		 }
	}
	

	/**
	 * portable way for syncronically loading a script
	 * taken from http://www.exit12.org/archives/12
	 * @j2sNative
if(window.XMLHttpRequest)
	var req = new XMLHttpRequest();
else
	var req = new ActiveXObject("Microsoft.XMLHTTP");
req.open("GET",jsFileLocation,false);
req.send(null);
if( window.execScript)
	window.execScript(req.responseText);
else
	window.eval(req.responseText);
	 */
	public void loadScript(String jsFileLocation) {}
	
	

	public static void main(String[]a){
		timerTest();
	}
	
	private static void timerTest() {
		Utils u = Utils.getInstance();
		System.out.println("0 ms");
		u.installTimer(new Runnable() {
			public void run() {
				System.out.println("2000 ms");
			}			
		}, 2000);
	}
	/**
	 * @j2sNative
	 * return true; 
	 */
	public static boolean isJs() {
		return false;
	}
	
	public static int randomNumberBetween(int a, int b) {
		if(b<=a || a<0 || b<0)//TODO: not supported
			return a;
		else {
			double c = Math.random()*(b-a);
			return (int) (Math.round(c)+a);
		}
	}
	
	public static LWMap<Object,Object> toLWMap(Object[][] a) {
		LWMap<Object,Object> m = new LWMap<Object,Object>();
		for (int i = 0; i < a.length; i++) {
			Object[] b = a[i];
			if(b.length<2)
				throw new RuntimeException("incorrect matrix size");
			m.put(b[0], b[1]);
		}
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
	
	public static Object toJavascript(Object[][] a) {
		return toLWMap(a)._map;
	}
	
//	/**
//	 * @return a java, javascrpipt, json array compatible repr.
//	 */
//	public static String toString(Object[] a) {
//		String s="[";
//		for(int i=0;i<a.length;i++) {
//			if(i!=a.length-1)
//				s=s+a[i]+",";	
//		}
//		return s+"]";
//	}
	/**
	 * @return a java, javascrpipt, json array compatible repr.
	 */
	public static String toString(double[] a) {
		String s="[";
		for(int i=0;i<a.length;i++) {
			if(i!=a.length-1)
				s+=a[i]+",";	
			else
				s+=a[i];
		}
		return s+"]";
	}
	
	/**
	 * @return a java, javascrpipt, json string array compatible repr.
	 */
	public static String toString(String[] a) {
		String s="[";
		for(int i=0;i<a.length;i++) {
			if(i!=a.length-1)
				s+=quote(a[i])+",";
			else 
				s+=quote(a[i]);				
		}
		return s+"]";
	}
	/**
	 * @return a java, javascrpipt, json string array compatible repr.
	 */
	public static String toString(Collection<String> a) {
		String s="[";
		Iterator<String> i = a.iterator();
		while(i.hasNext()){
			s+=quote(i.next());
			if(i.hasNext())
				s+=",";
		}
		return s+"]";
	}
	public static boolean arrayContains(Object[] args, Object string) {
		if(args==null)
			return false;
		for (int i = 0; i < args.length; i++) {
			if(args[i].equals(string))
				return true;
		}
		return false;
	}
	
	private static long count = 0;
	public static long getUnique() {
		count++;
		return count-1;
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
	
//	public static java.util.Map toJMap(LWMap m) {
//		if(m instanceof LWMap)
//			return (java.util.Map) ((LWMap)m)._map;
//		java.util.Map jm = new java.util.HashMap();
//		Iterator i = m.keys().iterator();
//		while(i.hasNext()){
//			Object k = i.next();
//			jm.put(k, m.get(k));
//		}
//		return jm;
//	}
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
			assertsFailed.add(assertMsg);
	}

	public static List toList(Object[] a) {
		List l = new LinkedList();
		for(int i=0; i<a.length; i++) {
			l.add(a[i]);
		}
		return l;
	}
//	public List<T> toList_(T[] a) {
//		return toList(a);
//	}
	public static Object[] toArray(List a) {
		Object [] t = new Object[a.size()];
		Iterator i = a.iterator();int j=0;
		while(i.hasNext()){
			t[j]=i.next();
			j++;
		}
		return t;
	}
	public static String[] toStringArray(Object[]o) {
		if(o==null)
			return null;
		String[] s = new String[o.length];
		for (int i = 0; i < o.length; i++) {
			s[i]=o[i].toString();
		}
		return s;
	}
//	public T[] toArray_(List<T> a) {
//		return (T[]) toArray(a);
//	}
	
	

	//	collection utils
	public static Collection select(Collection c, Predicate p) {
		List l = new LinkedList();
		Iterator i = c.iterator();
		while(i.hasNext()) {
			Object o = i.next();
			if(p.select(o))
				l.add(o);
		}
		return l;
	}	
	public static Collection select2(Collection c, Predicate2 p) {
		List l = new LinkedList();
		Iterator i = c.iterator();
		while(i.hasNext()) {
			Object o = i.next(), v=p.select(o);
			if(v!=null) {
				l.add(v);
			}
		}
		return l;
	}	
	
	public static Collection select2(Object[]c, Predicate2 p) {		
		List l = new LinkedList();
		for(int i=0; i<c.length; i++){ 
			Object o = c[i], v=p.select(o);
			if(v!=null) {
				l.add(v);
			}
		}
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
		Iterator i = objectToMap.keySet().iterator();
		String s = "[";
		while(i.hasNext()) {
			Object k = i.next();
			s=s+k+":\n"+objectToMap.get(k);
			if(i.hasNext())
				s=s+"\n\n";
		}
		return s+"]";
	}
	
	public static String quote(String s) {
		return "\""+s+"\"";
	}
	public static String byteArraytoString(byte[] a) {
			String s="[";
			for(int i=0;i<a.length;i++) {
				if(i!=a.length-1)
					s=s+((int)a[i])+",";	
			}
			return s+"]";
	}
	public static InputStream stringToStream(String s) {
		return new ByteArrayInputStream(s.getBytes());
	}
	public static Object castOrThrow(Object value, Class class1,
			String msg) {
		if(value.getClass().equals(class1)) {
			return value;
		}
		else
			throw new RuntimeException(msg+". "+value.getClass()+" received and "+class1+" espected");
	}
		
}
