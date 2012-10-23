package org.sgx.editor.model1_nosirve;

public class EditorProperty {
public EditorProperty(String name, String description, Object value) {
		super();
		this.name = name;
		this.description = description;
		this.value = value;
	}
public EditorProperty(Object value) {
	this(null,null,value);
}
public EditorProperty(String name, String description) {
	this(name, description, null);
}
/**
 * can be null
 */
String name;
/**
 * can be null
 */
String description;
Object value;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Object getValue() {
	return value;
}
public void setValue(Object value) {
	this.value = value;
}
}
