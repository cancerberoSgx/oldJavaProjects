package org.sgx.j2s.widget.events;

public class PropChangeEvent extends Event {
String propName;
public String getPropName() {
	return propName;
}
public Object getNewValue() {
	return newValue;
}
public PropChangeEvent(String propName, Object newValue) {
	super();
	this.propName = propName;
	this.newValue = newValue;
}
Object newValue;
}
