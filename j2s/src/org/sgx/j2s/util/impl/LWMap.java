package org.sgx.j2s.util.impl;

import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.util.Collection;
import org.sgx.j2s.util.Iterator;
import org.sgx.j2s.util.List;
import org.sgx.j2s.util.Map;
/**
 * implementación de un map utilizando un objeto javascript. 
 * we compare using equals method (javascript ==  is not used). 
 * in javascript, if a value asociated with a key is null that means that the maping doesn't exists for that key
 * 
 * @author SGURIN
 * 
 * TODO: check null keys and value consistency with java.util.map spec
 * 
 *@j2sRequireImport org.sgx.j2s.js.JsUtils
 */
public class LWMap<K,V> implements Map<K,V> {

	public Object _map;
	
 
	 public  LWMap() {
//		 System.out.println("constr");
		 _map=JsUtils.getJsEmptyObject();
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
	
	public Collection<K> keys() {
		List<K> l = new LinkedList<K>();
		/**
		 * @j2sNative
		 * for(var i in this._map) 
		 * 	if(this._map[i]!=null)
		 * 		l.append(i);
		 * return l;
		 */{ 
			 java.util.Iterator<K> i = ((java.util.Map<K,V>)_map).keySet().iterator();
			 while(i.hasNext())
				 l.append(i.next());
			 return l;
		 }		
	}
	/**
	 * TODO: hacer mas a bajo nivel para optimizar
	 */
	public Collection<V> values() {
		Iterator<K> i = keys().iterator();
		List<V> l = new LinkedList<V>();
		while(i.hasNext())  {
			l.append(get(i.next()));
		}
		return l;		
	}
	
	public String toString() {
		Iterator<K> i = keys().iterator();
		String s = "[";
		while(i.hasNext()) {
			K k = i.next();
			s=s+k+"->"+get(k);
			if(i.hasNext())
				s=s+", ";
		}
		return s+"]";
	}
	
	//debug
	public Object get_map() {
		return _map;
	}
	
}
