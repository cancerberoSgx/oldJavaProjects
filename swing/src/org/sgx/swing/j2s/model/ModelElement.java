package org.sgx.swing.j2s.model;

import java.util.Map;

import org.sgx.swing.j2s.model.events.EventTarget;
import org.sgx.swing.j2s.model.events.PropertyChangeEvent;
import org.sgx.swing.j2s.model.events.PropertyChangeListener;

public interface ModelElement  extends EventTarget {
Object get(String pid);
void set(String pid, Object val);
/**
 * properties 
 * must be a  propId->propClass map 
 * see modelelementimpltest
 * @return
 */
Map<String, Class>getProperties();
void notifyPropertyChange(PropertyChangeEvent e);
void addPropertyChangeListener(String propId, PropertyChangeListener l);

public boolean isNotifyChangeProperty();
public void setNotifyChangeProperty(boolean notifyChangeProperty);
}
