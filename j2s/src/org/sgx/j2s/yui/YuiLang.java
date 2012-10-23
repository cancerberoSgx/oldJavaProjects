package org.sgx.j2s.yui;

/**
 *  Class YAHOO.lang
Provides the language utilites and extensions used by the library 
 * @author sgurin
 *
 */
public class YuiLang {
	
//	static {
//		ScriptLoader.loadYUI_dom_events();
//	}
	
	/**
	 * Parameters:
    o <Object> The object to dump 
    d <int> How deep to recurse child objects, default 3
@j2sNative 
return YAHOO.lang.dump(o,d);
	 */
	public static  String dump  (Object o ,int d ) {return null;}

//	static void extend  ( subc , superc , overrides ) //TODO
	
	/**
@j2sNative 
return YAHOO.lang.hasOwnProperty(o,prop);
	 */
	public static  boolean hasOwnProperty  (Object o, String prop ){return false;}
	
	/**
	 * Determines whether or not the provided object is an array. Testing typeof/instanceof/constructor of arrays across frame boundaries isn't possible in Safari unless you have a reference to the other frame to test against its Array prototype. To handle this case, we test well-known array properties instead. properties.

	 Parameters:
	     o <any> The object being testing 
@j2sNative 
return YAHOO.lang.isArray(o);
	*/
	public static boolean isArray  (Object o ) {return false;}
	 
	/**
	 Determines whether or not the provided object is a boolean
	 Parameters:
	     o <any> The object being testing 
@j2sNative 
return YAHOO.lang.isBoolean(o);
	*/
	public static boolean isBoolean  (Object o ) {return false;}
	
	
	/**	 Determines whether or not the provided object is a function

	 Parameters:
	     o <any> The object being testing 
@j2sNative 
return YAHOO.lang.isFunction(o);
	*/
	public static boolean isFunction  (Object o ) {return false;}
	
	/**	 Determines whether or not the provided object is null
@j2sNative 
return YAHOO.lang.isNull(o);
	*/
	public static boolean isNull  (Object o ) {return false;}
	/**	 Determines whether or not the provided object is a number
	@j2sNative 
	return YAHOO.lang.isNumber(o);
	*/
	public static boolean isNumber  (Object o ) {return false;}
	/**	 Determines whether or not the provided object is a object
	@j2sNative 
	return YAHOO.lang.isObject(o);
	*/
	public static boolean isObject  (Object o ) {return false;}
	/**
	 * @j2sNative
	 * return  YAHOO.lang.isString(o);
	 */
	 public static boolean isString  (Object o ) {return false; }
	 /**
	 * @j2sNative
	 * return  YAHOO.lang.isUndefined(o);
	 */
	 public static boolean isUndefined  (Object o ) {return false; }
	 /**A convenience method for detecting a legitimate non-null value. Returns false for null/undefined/NaN, true for other values, including 0/false/''
	 * @j2sNative
	 * return  YAHOO.lang.isValue(o);
	 */
	 public static boolean isValue  (Object o ) {return false; }
	 
	 /**Executes the supplied runnable  'when' milliseconds later passing the 
	  * supplied data object. Executes the function a single time unless 
	  * periodic is set to true.
	 * @j2sNative
	 * return  YAHOO.lang.later(when, r, r.run, data, periodic);
	 */
	 public static YuiTimer later (int when , Runnable1Arg r, Object data, boolean periodic){return null;}
	 
//	  boolean isNull  ( o )
//	  Determines whether or not the provided object is null
//
//	  Parameters:
//	      o <any> The object being testing 
//
//	  Returns: boolean
//	      the result

	
	 
	 


	
}
