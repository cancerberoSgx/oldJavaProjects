package org.sgx.utils.reflection;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * un mapas cuyas claves mantienen el orden en el cual fueron agregadas (put)
 * @author sgurin
 *
 * @param <K> clave
 * @param <V> valor
 */
public class OrderedMap<K,V> extends HashMap<K,V>{
//	Map<K, V> map = new HashMap<K, V>();
	List<K> keys = new LinkedList<K>();
//	public V get(K k) {
//		return map.get(k);
//	}
	
	public V put(K key, V value) {
		V v = super.put(key,value);
		if(keys.contains(key)) 
			keys.remove(key);
		keys.add(key);
		return v;
	};
	
//	public void put(K k, V v) {
//		map.put(k, v);
//		if(keys.contains(k)) 
//			keys.remove(k);
//		keys.add(k);
//	}
	public List<K> getOrderedKeys() {		
		return keys;
	}
	public void setOrder(List<K>keys) {
		throw new RuntimeException("not implemented");
	}

	public List<V> getOrderedValues() {
		List<V> l = new LinkedList<V>();
		for(K k : keys) {
			l.add(get(k));
		}
		return l;
	}
	
	@Override
	public String toString() {
		String s = "{";
		for(K k : keys) {
			s+=k+":"+get(k)+", ";
		}
		return s+"}";
	}
}
