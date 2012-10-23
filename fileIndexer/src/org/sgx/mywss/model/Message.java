package org.sgx.mywss.model;

import java.util.Map;

import org.sgx.json.JSONObject;
/**
 * (server and client side)
 * @author sg
 *
 */
public class Message {
String name;
Map<String, JSONObject> parameters;
public String getName() {
	return name;
}
public Map<String, JSONObject> getParameters() {
	return parameters;
}
public Message(){}
public Message(String name, Map<String, JSONObject> parameters) {
	super();
	this.name = name;
	this.parameters = parameters;
}
public void setName(String name) {
	this.name = name;
}
public void setParameters(Map<String, JSONObject> parameters) {
	this.parameters = parameters;
}
}
