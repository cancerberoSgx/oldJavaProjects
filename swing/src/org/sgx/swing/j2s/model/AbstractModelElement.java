package org.sgx.swing.j2s.model;

import java.util.*;

//import org.sgx.j2s.util.*;
//import org.sgx.j2s.util.impl.*;
import org.sgx.swing.j2s.model.events.*;
import org.sgx.swing.j2s.model.util.ModelHelper;
import org.sgx.utils.Utils;
import org.sgx.utils.Block;

/**
 * this model elemnt impl uses object properties in javascript and reflection in java for 
 * dynamically  setting properties values
 * 
 * this means that property names must be valid javabean prop names i.e: the methods get${PropName} 
 * must be a valid java method nmame. also attributes that represent properties must have the same 
 * name as the property
 * 
 * implementators must be carefull of notify prop change observers in setters methods. for that, 
 * _beforeSet and _afterSetsee helper methods can be used in side setters (see setters in modelelementimpl)
 * 
 * @author SGURIN
 *
 */
public abstract class AbstractModelElement implements ModelElement{

	boolean notifyChangeProperty=true;
	
	public boolean isNotifyChangeProperty() {
		return notifyChangeProperty;
	}

	public void setNotifyChangeProperty(boolean notifyChangeProperty) {
		this.notifyChangeProperty = notifyChangeProperty;
	}

	public Object get(String pid) {
		return ModelHelper.get(this, pid);
	}

	public void set(String pid, Object val) {
		ModelHelper.set(this, pid, val);	
	}
	
	protected void _afterSet(String pid) {
		if(notifyChangeProperty) {
			this.notifyPropertyChange(new PropertyChangeEvent(this, pid, get(pid)));
		}
	}
	
	public void notifyPropertyChange(final PropertyChangeEvent e) {
		List<PropertyChangeListener> elisteners = propListeners.get(e.getPid());
		if(elisteners!=null)
			Utils.foreach(elisteners, new Block () {
				public Object eval(Object o) {
					 ((PropertyChangeListener)o).notifyPropertyChange(e);
					 return null;
				}	
			});
	}

	private Map <String, Class> _props = null;
	public Map<String, Class> getProperties(){
		if(_props==null)
			_props = ModelHelper.getProperties(this);
		return _props;
	}
	
	Map<String, List<PropertyChangeListener>> propListeners= new HashMap<String, List<PropertyChangeListener>>();
	public void addPropertyChangeListener(String propId,
			PropertyChangeListener l) {
		List<PropertyChangeListener> listeners = propListeners.get(propId);
		if(listeners==null) {
			listeners=new LinkedList<PropertyChangeListener>();
			propListeners.put(propId, listeners);
		}
		listeners.add(l);		
	}
	
//	List<EventListener> elisteners = new LinkedList<EventListener>();
//	
//	public void addEventListener(EventListener l) {
//		elisteners.append(l);
//	}
//	public void removeEventListener(EventListener l) {
//		elisteners.remove(l);
//	}

}
