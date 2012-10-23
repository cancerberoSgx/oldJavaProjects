package org.sgx.j2s.widget;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * high performance classification system.
 * @author SGURIN
 *
 */
public class WidgetClassManager {
	Map<Object, Set<String>> objectClasses;
	Map<String, Set<Object>> classObjects;
	static WidgetClassManager instance;
	public static WidgetClassManager getInstance(){
		if(instance==null)
			instance= new WidgetClassManager();
		return instance;
	}
	private WidgetClassManager(){
		objectClasses = new HashMap<Object, Set<String>>();
		classObjects = new HashMap<String, Set<Object>>();
	}
	private void registerObject(Object o){
		if(!objectClasses.containsKey(o)){
			objectClasses.put(o, new HashSet<String>());
		}	
	}
	private void registerClass(String cl){
		if(!classObjects.containsKey(cl)){
			classObjects.put(cl, new HashSet<Object>());
		}	
	}
	public void addClass(Object o, String cl){
		registerObject(o);
		registerClass(cl);
		Set<String> classes = objectClasses.get(o);
		if(!classes.contains(cl))
			classes.add(cl);
	}
	public void removeClass(Object o, String cl){
		registerObject(o);
		registerClass(cl);
		Set<String> classes = objectClasses.get(o);
		if(classes.contains(cl))
			classes.remove(cl);
	}
	public Set<String> getClasses(Object o){
		registerObject(o);
		return objectClasses.get(o);
	}
	public boolean hasClass(Object o, String cl){
		registerObject(o);
		registerClass(cl);
		return objectClasses.get(o).contains(cl);
	}

	public Set<Object> getObjectOf(String cl){
		registerClass(cl);
		return classObjects.get(cl);
	}
	
	public Set<Object> getObjectOf(Set<String> classes) {
		Set<Object> S  = new HashSet<Object>();
		for(String cl : classes){
			Set<Object> set1 = getObjectOf(cl);
			S.addAll(set1);
		}
		return S;
	}

}
