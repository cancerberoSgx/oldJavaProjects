package org.sgx.mywss;

import static org.sgx.j2s.utils.Utils.quote;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.sgx.j2s.utils.Utils;
import org.sgx.json.JSONHome;
import org.sgx.json.JSONObject;
import org.sgx.mywss.model.Message;
import org.sgx.mywss.model.POJOMethod;
import org.sgx.mywss.servlet.MYWSServlet;

public class MYWSSUtils {

	/* *******   server side - sevlet ******/
	
	private static final String PARAM_NAME_RETRIEVER_POSTFIX = "_param_list";
	private static final String PARAM_NAME_RETRIEVER_SEPARATOR = ",";
	/**
	 * FIXME: to be correct this has to be done using a model.Response object 
	 */
	public static String buildNormalResponse(String response) {
		return "{"+quote(MYWSServlet.ATTR_RESPONSE_ERROR)+":"+"false"+","+
		quote(MYWSServlet.ATTR_RESPONSE)+":"+response+"}";
	}

	/**
	 * FIXME: to be correct this has to be done using a model.Response object 
	 */
	public static String buildErrorResponse(Exception e) {
		return "{"+quote(MYWSServlet.ATTR_RESPONSE_ERROR)+":"+quote(e.getClass()+
				" - "+e.getMessage())+"}";
	}
	
	

	// Java - reflection - mywss - json related utilities //
	
	public static org.sgx.mywss.model.Method[] java2WSMethods(Object pojo) throws Exception {
		java.lang.reflect.Method[] mtds = pojo.getClass().getMethods();
		List<org.sgx.mywss.model.Method> wsmts = new LinkedList<org.sgx.mywss.model.Method>(); 
		for (int i = 0; i < mtds.length; i++) {
			java.lang.reflect.Method javaMethod = mtds[i];
			if(!isPublicMethod(javaMethod))
				continue;
			wsmts.add(MYWSSUtils.java2WSMethod(javaMethod, pojo));			
		}
		return (org.sgx.mywss.model.Method[]) wsmts.toArray(new org.sgx.mywss.model.Method[wsmts.size()]);
	}

	public static org.sgx.mywss.model.Method java2WSMethod(Method javaMethod, Object pojo) throws Exception {
		String returnType = java2WSType(javaMethod.getReturnType());
		String name= javaMethod.getName();
		return new POJOMethod(name, pojo);
	}
	public static String java2WSType(Class<?> type) {
		if(type==null || type.toString().equals("void")){
			return null;
		}
		else if(type.isArray()){
			return org.sgx.mywss.model.Method.TYPE_ARRAY;
		}
		else if(type.equals(String.class)) {
			return org.sgx.mywss.model.Method.TYPE_STRING;
		}
		else if(type.equals(Boolean.class)) {
			return org.sgx.mywss.model.Method.TYPE_BOOLEAN;
		}
		else if(type.equals(Number.class)) {
			return org.sgx.mywss.model.Method.TYPE_NUMBER;
		}
		else {
			return org.sgx.mywss.model.Method.TYPE_OBJECT;
		}
	}

	public static Map<String, String> java2WSattributeTypes(Method jmethod) {
		Map<String, String> m=new HashMap<String, String>();
		TypeVariable<Method>[] tps = jmethod.getTypeParameters();
		Class<?>[] ptypes = jmethod.getParameterTypes();
		for (int i = 0; i < tps.length; i++) {
			TypeVariable<Method> tp = tps[i];
			Class pclass = ptypes[i];		
			String pname = tp.getName();
			m.put(pname, MYWSSUtils.java2WSType(pclass));
		}
		return m;
	}
	public static boolean isPublicMethod(Method m) {
		return Modifier.toString(m.getModifiers()).contains("public");
	}
	public static boolean isStaticMethod(Method m) {
		return Modifier.toString(m.getModifiers()).contains("static");
	}
	/**
	 * @param castToClass solo puede ser una de String.class, Collection.class, Boolean.class, 
	 * 	Number.class o Object.class
	 * @param exceptionMsg int he case that casting fails, this is the message that will decorethe exception
	 * @return in the case of Object.class it will return a Map<String, JSONObject>, in the case of an array it will return a List<JSONObject>
	 */
	public static Object castOrThrowException(JSONObject o,
			Class castToClass, String exceptionMsg) throws Exception{
		if(castToClass.equals(String.class)){
			if(!o.isString()) 
				throwException(castToClass, exceptionMsg);
			else
				return o.castToString();			
		}
		else if(castToClass.isArray() || castToClass.equals(Collection.class)) {
			if(!o.isArray()) 
				throwException(castToClass, exceptionMsg);
			else
				return o.castToArray(); //(List)	
		}
		else if(castToClass.equals(Number.class)){
			if(!o.isNumber()) 
				throwException(castToClass, exceptionMsg);
			else
				return o.castToNumber();			
		}
		else if(castToClass.equals(Boolean.class)){
			if(!o.isBoolean()) 
				throwException(castToClass, exceptionMsg);
			else
				return o.castToBoolean();			
		}
		else if(castToClass.equals(Object.class)){
			if(!o.isObject()) 
				throwException(castToClass, exceptionMsg);
			else
				return o.castToObject();	
		}
		return null;
	}
	/**@return in the case of Object.class it will return a Map<String, JSONObject>, 
	 * in the case of an array it will return a List<JSONObject>
	 */
	public static Object getJavaObject(JSONObject o){
		if(o.isString()) 
			return o.castToString();			
		else if(o.isArray()) 
			return o.castToArray(); 	
		else if(o.isNumber()) 
			return o.castToNumber();			
		else if(o.isBoolean()) 
			return o.castToBoolean();	
		else if(o.isObject()) 
			return o.castToObject();
		else 
			throw new RuntimeException("mmmmmmm.... impossible");
	}
	
	/**@throws Exception if o can be serialized (unsupported type)
	 * normalizes a common pojo for being serialized
	 */
	public static Object getJavaObject2(Object o) throws Exception{
		if(o instanceof JSONObject) {
			return getJavaObject((JSONObject) o);
		}
		else if(o instanceof String|| o instanceof Number || o  instanceof Boolean ||
				o ==null){
			return o;
		}
		if(o instanceof List) {
			return (List)o;
		}
		else if(o.getClass().isArray()) {
			return o;			
		}
		else if(o instanceof Map){
			return (Map)o;
		}
		else if(o ==null){
			return null;
		}
		else{
			throw new Exception("Unsupported object :"+o+" - type: "+o.getClass().getName());
		}
	}
	
	public static void throwException(Class<Exception> castToClass, String exceptionMsg) throws Exception {
		Exception e = castToClass.newInstance();
		throw new Exception(exceptionMsg, e);
	}
	/**
	 * this function is able to represent any of the following types recursively:
	 * java.util.List for arrays, java.util.Map for objects, String, Number, Boolean and null.
	 */
	public static String toJSON(Object o) {
		if(o instanceof List) {
			return org.json.simple.JSONArray.toJSONString((List)o);
		}
		else if(o.getClass().isArray()) {
			return org.json.simple.JSONArray.toJSONString(Utils.toList(((Object[])o)));			
		}
		else if(o instanceof Map){
			return org.json.simple.JSONObject.toJSONString((Map)o);
		}else{
		//		return JSONHome.getInstance().writeToJSON(p);
			return org.json.simple.JSONValue.toJSONString(o);
		}
	}

	public static Method getMethodNamed(Class<? extends Object> class1, String name) {
		Method[] mts = class1.getMethods();
		for (int i = 0; i < mts.length; i++) {
			if(mts[i].getName().equals(name))
				return mts[i];
		}
		return null;
	}
//	public static Map<String, Integer> getParameterOrder(Method jmethod) {
//		Map<String, Integer> m = new HashMap<String, Integer> ();
//		TypeVariable<Method>[] params = jmethod.getTypeParameters();
//		System.out.println(params.length);
//		for (int i = 0; i < params.length; i++) 
//			m.put(params[i].getName(), i);
//		return m;	
//	}

	/**
 * because java reflection doesn'0t support getting parameter 
 * names in method declarations, the user have to implement an 
 * extra method String $METHODNAME_param_list(). see PojoMethodTest. 
 * this is the responsible operation for extracting parameter name -> index  
	 * @throws Exception 
	 */
	public static Map<String, Integer> getMethodParamIndex(Object pojo, Method jmethod) throws Exception {
		String retrieverName = jmethod.getName()+MYWSSUtils.PARAM_NAME_RETRIEVER_POSTFIX;
		Method retrieverMethod = pojo.getClass().getMethod(retrieverName, new Class[]{});
		Object  respObj = retrieverMethod.invoke(pojo, new Object[]{});
		if(! (respObj instanceof String))
			throw new Exception("MYWSS Error: Bad return type for retriever method "+retrieverName+". must be string. ");
		String[] paramNames = ((String)respObj).split(MYWSSUtils.PARAM_NAME_RETRIEVER_SEPARATOR);
		Map<String, Integer> m = new HashMap<String, Integer>();
		for (int i = 0; i < paramNames.length; i++) {
			m.put(paramNames[i], i);
		}
		return m;
	}
	
//	public static String[] getParameterNames(Method m) {
//		m.getAnnotation(MYWSSMethodInfo.)
//	}

}
