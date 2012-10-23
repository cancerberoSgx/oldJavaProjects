package org.sgx.j2s.widget.base;

public interface PropertyOwner {
String[] getProperties();
Object getProperty(String name);
void setProperty(String name, Object value);
}
