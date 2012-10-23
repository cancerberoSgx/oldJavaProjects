package org.sgx.json;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * JSONObject impl using org.json.simple
 * 
JSON 	 Java
string 	java.lang.String
number 	java.lang.Number
true|false 	java.lang.Boolean
null 	null
array 	java.util.List
object 	java.util.Map

JSON.simple maps entities from the left side 

 * @author sg
 *
 */
public class JSONObjectSimpleImpl implements JSONObject{
	Object _data=null;

	public JSONObjectSimpleImpl(Object object) {
		_data = object;
	}
	public JSONObjectSimpleImpl() {
		// TODO Auto-generated constructor stub
	}
	public boolean isArray() {
		return _data instanceof java.util.List;
	}
	public List<JSONObject> castToArray() {	
		List<JSONObject> l = new LinkedList<JSONObject>(); 
		List data = (List) _data;
		for(Object obj: data){
			l.add(new JSONObjectSimpleImpl(obj));
		}
		return (java.util.List)l;
	}

	public boolean isNull(){
		return _data==null;
	}

	public boolean isObject() {
		return _data instanceof Map;
	}

	public void load(String json) throws Exception {
		JSONParser parser = new JSONParser();
		_data = parser.parse(json);
	}
	public boolean isBoolean() {
		return _data instanceof Boolean;
	}
	public Boolean castToBoolean() {
		return (Boolean) _data;
	}

	public boolean isNumber() {
		return _data instanceof Number;
	}
	public Number castToNumber() {
		return (Number) _data;
	}

	public Map<String, JSONObject> castToObject() {
		Map<String, JSONObject> m = new HashMap<String, JSONObject>();
		Map data=(Map) _data;
		for (Object key : data.keySet()) {
			m.put((String) key, new JSONObjectSimpleImpl(data.get(key)));
		}
		return m;
	}
	public static JSONObject parse(String json) throws Exception {
		JSONObject o = new JSONObjectSimpleImpl();
		o.load(json);
		return o;
	}
	public String castToString() {
		return (String)_data;
	}
	public boolean isString() {
		return _data instanceof String;
	}
//	public Object castToJavType() {
//		if(isString())
//			return castToString();
//		else if(isNumber())
//			return castToNumber();
//		else if(isBoolean())
//			return castToBoolean();
//		else if(isObject()){
//			return _data;
//			Map<String, JSONObject> m = castToObject();
//			for (Iterator iterator = m.keySet().iterator(); iterator.hasNext();) {
//				
//				type type = (type) iterator.next();
//				
//			}
//		}
//		else if(isArray())
//			return castToArray();
//		else if(isNull())
//			return null;
//		else return null;
//	}	


}
