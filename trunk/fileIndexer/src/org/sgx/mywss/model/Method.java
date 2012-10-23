package org.sgx.mywss.model;

import java.util.HashMap;
import java.util.Map;

import org.sgx.j2s.utils.Utils;
import org.sgx.mywss.MessageNotUnderstoodException;

/**
 * i'm responsible of representin a method signature and dispatch incoming messages
 *  
 * @author sgx
 *
 */
public abstract class Method {
	public static final String TYPE_NUMBER="number", 
		TYPE_STRING="string", TYPE_ARRAY="array", 
		TYPE_OBJECT="object", TYPE_BOOLEAN="boolean";
	
	public String name;
	public Map<String, String> attributeTypes;
	public String returnType;
	public String comment;
	
	public Method(String name, Map<String, String> parameters,
			String returnType, String comment) {
		super();
		this.name = name;
		this.attributeTypes = parameters;
		this.returnType = returnType;
		this.comment=comment;
	}
	public String getName() {
		return name;
	}
	public Map<String, String> getAttributeTypes() {
		return attributeTypes;
	}
	public String getReturnType() {
		return returnType;
	}

	public abstract String dispatchMessage(Message m) throws Exception;
	
	public Map toMap(){
		return Utils.toMap(new Object[][]{{"name", name}, {"params",attributeTypes}, {"returnType",returnType}, {"comment",comment}});		
	}
}
