package org.sgx.picturemakeup.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Cambiarme el nombre please!!
 * @author sgx
 *
 */
public abstract class PropertyHaver {

	List<PropertyListener> listeners= new LinkedList<PropertyListener>();
	
	/*
	 * no olvidar de invocar a notifyall()
	 */
	public abstract void  changePropertyValue(String propId, Object value);
	
	public abstract Object getPropertyValue(String propertyId);
	
	public abstract Property[] getProperties();
	
	public void register(PropertyListener l){
		listeners.add(l);
	}
	
	public void notifyAll(String propertyId, Object value) {
		Iterator<PropertyListener> i = listeners.iterator();
		while(i.hasNext()) {
			i.next().notifyPropertyChange(propertyId, value);
		}
	}

	public float castValueToFloat(Object value) {
		if(value instanceof Number)
			return ((Number)value).floatValue();
		else throw new RuntimeException("bad value type");			
	}
	public double castValueToDouble(Object value) {
		if(value instanceof Number)
			return ((Number)value).doubleValue();
		else throw new RuntimeException("bad value type");			
	}
	public int castValueToInt(Object value) {
		if(value instanceof Number)
			return ((Number)value).intValue();
		else throw new RuntimeException("bad value type");	
	}
	
//	public Property getPropertyNamed()
//	public void changePropertyValue(String id, Object value) {
//		for (int i = 0; i<getProperties().length; i++) {
//			Property p = getProperties()[i];
//			if(p.getId().equals(id)) { 
//				p.setDefaultValue(value);
//				changePropertyValue(p);
//			}
//		}
//		
//	}
	
}
