package org.sgx.json;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * wrapper for different json libraries
 * @author sgx
 *
 */
public interface JSONObject {
	void load(String json) throws Exception;	
	 boolean isArray();	 
	 List<JSONObject> castToArray();
	 boolean isObject();
	 Map<String, JSONObject> castToObject();
	 boolean isNumber();
	 Number castToNumber();
	 boolean isBoolean();	 
	 Boolean castToBoolean();
	 boolean isString();
	 String castToString();
	 boolean isNull();
//	 /**
//	  * 
//	  * @return only java supported types (Boolean, String, Number, Map) corresponding to represented object 
//	  */
//	Object castToJavType();
}
