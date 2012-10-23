package org.sgx.j2s.util;


/**
 * lightwiehgt map (use pure javascript object in javascript runtime, use hashmap in java)
 * 
 * @author SGURIN
 * 
 * TODO: check null keys and value consistency with java.util.map spec
 * 
 */
public class LWMap<K,V>{

	public Object _map;
	
	private Object cleanJsObject() {
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
	 public  LWMap() {
		 _map=cleanJsObject();
		 /**
		 * @j2sNative
		 */{
			 _map=new java.util.HashMap<K, V>();
		 }
	 }
	/**
	 * @j2sNative
	 */
	public void clear() {
		((java.util.Map<K,V>)_map).clear();
	}
	/**
	 * @j2sNative
	 * return this._map[key]!=null;
	 */
	public boolean containsKey(Object key) {
		return ((java.util.Map<K,V>)_map).containsKey(key);
	}
	/**
	 * javascript implementation: O(n)
	 * @j2sNative
	 * if(value==null)
	 * 	return false;
	 * for(var i in this._map) {
	 * 	if(this._map[i].equals(value))
	 * 		return true;
	 * }
	 * return false;
	 */
	public boolean containsValue(Object value) {
		return ((java.util.Map<K,V>)_map).containsValue(value);
	}
	/**
	 * @j2sNative
	 * return this._map[key]
	 */
	public V get(Object key) {
		return ((java.util.Map<K,V>)_map).get(key);
	}
	/**
	 * @j2sNative
	 * 	for(var i in this._map)
	 * 		return false;
	 * 	return true;
	 */
	public boolean isEmpty() {
		return ((java.util.Map<K,V>)_map).isEmpty();
	}
	
	/** 
	 * @j2sNative
	 * var pval = this._map[key];
	 * this._map[key]=value;
	 * return pval;
	 */
	public V put(K key, V value) {
		return ((java.util.Map<K,V>)_map).put(key, value);
	}

	/**
	 * @j2sNative
	 * var pval = this._map[key];
	 * this._map[key]=null
	 * return pval;
	 */
	public V remove(Object key) {
		return ((java.util.Map<K,V>)_map).remove(key);
	}
	
	public K[] keys() {
		
		/**
		 * @j2sNative
		 * var a = [];
		 * for(var i in this._map) 
		 * 	if(this._map[i]!=null)
		 * 		a.push(i)
		 * return a;
		 */{ 
			 java.util.ArrayList<K> l = new java.util.ArrayList<K>();
			 java.util.Iterator<K> i = ((java.util.Map<K,V>)_map).keySet().iterator();
			 while(i.hasNext())
				 l.add(i.next());
			 return (K[]) l.toArray();
		 }
	}
	/**
	 * TODO: hacer mas a bajo nivel para optimizar
	 */
	public V[] values() {
		K[] keys = keys();
		V[] values = (V[]) new Object[keys.length];
		for (int i = 0; i < keys.length; i++) {
			values[i]=get(keys[i]);			
		}
		return values;		
	}
	
	public String toString() {
		String s = "[";
		K[] keys = keys();
		for (int j = 0; j < keys.length; j++) {
			K k = keys[j];
			s=s+k+"->"+get(k);
			if(j<keys.length-1)
				s=s+", ";
		}
		return s+"]";
	}
	
//	//debug
//	public Object get_map() {
//		return _map;
//	}
//	
}
