
package org.sgx.j2s.utils;

import java.util.Map;

import org.sgx.j2s.utils.serialization.Deserializator;
import org.sgx.j2s.utils.serialization.ParseException;
/**
 * @j2sRequireImport java.lang.reflect.Modifier
 *
 */

public class DataHelper {

	//static Class[] datatypes = {String.class, Double.class, Float.class, Integer.class, Boolean.class};
	static DataHelper instance;
	public static DataHelper getInstance() {
		if(instance==null)
			instance=new DataHelper();
		return instance;
	}
	private DataHelper(){
		init();
	}

	/**
	 * this function defines what a datatype is
	 */
	public boolean isDatatype(Object o) {
		/**
		 * @j2sNative
		 * if(o typeof 'string' || o typeof 'number' || o typeof 'boolean')
		 * 	return true;
		 */{}
		return o instanceof String 
		|| o instanceof Integer|| o instanceof Long|| o instanceof Short
		|| o instanceof Byte|| o instanceof Character|| o instanceof Double
		|| o instanceof Float || o instanceof Boolean;		
	}
	/**
	 * prec: isDatatype(o)
	 * @param o
	 * @return
	 */
	public String serializeDatatype(Object o) {
		if(o instanceof String)
			return Utils.quote(o.toString());
		else
			return o.toString();
	}

	Map<Object, Object> _deserializators = null;
	Map<Object, Object> datatypesNames = null;
	public static final String CLASSNAME_STRING = "java.lang.String",
		CLASSNAME_DOUBLE="java.lang.Double", CLASSNAME_FLOAT="java.lang.Float",
		CLASSNAME_BYTE="java.lang.Byte",CLASSNAME_INTEGER="java.lang.Integer",
		CLASSNAME_CHARACTER="java.lang.Character",CLASSNAME_BOOLEAN="java.lang.Boolean",
		CLASSNAME_SHORT="java.lang.Short",CLASSNAME_LONG="java.lang.Long";
	
	private void init() {
		_deserializators=Utils.transformArrayToMap(new Object[][]{
				{CLASSNAME_STRING, new Deserializator() {
					public Object deserialize(String s) {return s;}					
				}},
				{CLASSNAME_DOUBLE, new Deserializator() {
					public Object deserialize(String s) {return new Double(s);}					
				}},
				{CLASSNAME_FLOAT, new Deserializator() {
					public Object deserialize(String s) {return new Float(s);}					
				}},
				{CLASSNAME_BYTE, new Deserializator() {
					public Object deserialize(String s) {return new Byte(s);}					
				}},
				{CLASSNAME_INTEGER, new Deserializator() {
					public Object deserialize(String s) {return new Integer(s);}					
				}},
				{CLASSNAME_CHARACTER, new Deserializator() {
					public Object deserialize(String s) {
						if(s.length()>0) return new Character(s.charAt(0));
						else return null;
					}					
				}},
				{CLASSNAME_BOOLEAN, new Deserializator() {
					public Object deserialize(String s) {return new Boolean(s);}					
				}},
				{CLASSNAME_SHORT, new Deserializator() {
					public Object deserialize(String s) {return new Short(s);}					
				}},
				{CLASSNAME_LONG, new Deserializator() {
					public Object deserialize(String s) {return new Long(s);}					
				}},
		});
//		datatypesNames=Utils.transformArrayToMap(new Object[][]{
//		{String.class, "java.lang.String", new Deserializator() {
//		public Object deserialize(String s) {
//		return s;
//		}					
//		}},
	}
	public Object deserializeDatatype(String s, String className) throws ParseException {
		Object o = null;
		Deserializator d = (Deserializator) _deserializators.get(className);
		if(d!=null)
			return d.deserialize(s);
		else
			throw new RuntimeException("is not a datatype: "+className);
	}
	public String getDatatypeName(Object o) {
		return null;
//		if(o instanceof Integer) 
	}



	public boolean isArray(Object o) {
		if(isEmpty(o))
			return false;
		if(JsUtils.isJs())
			return (o instanceof Object[]);
		else
			return o.getClass().isArray();
	}


	/**
	 * java  primitive array to Object[] conversion deficiency
	 * @j2sNative
	 * return o;
	 */
	public Object[] castArray(Object o) {
		Object[]ia=null;
		if(o==null || !isArray(o))
			return null;
		else if (o instanceof Object[])
			return (Object[])o;
		else if(o instanceof int[]){
			int[] a = (int[])o;
			ia = new Integer[a.length];
			for (int i = 0; i < a.length; i++) 
				ia[i]=a[i];				
		}
		else if(o instanceof short[]){
			short[] a = (short[])o;
			ia = new Short[a.length];
			for (int i = 0; i < a.length; i++) 
				ia[i]=a[i];				
		}
		else if(o instanceof long[]){
			long[] a = (long[])o;
			ia = new Long[a.length];
			for (int i = 0; i < a.length; i++) 
				ia[i]=a[i];				
		}
		else if(o instanceof float[]){
			float[] a = (float[])o;
			ia = new Float[a.length];
			for (int i = 0; i < a.length; i++) 
				ia[i]=a[i];				
		}
		else if(o instanceof double[]){
			double[] a = (double[])o;
			ia = new Double[a.length];
			for (int i = 0; i < a.length; i++) 
				ia[i]=a[i];
		}
		else if(o instanceof char[]){
			char[] a = (char[])o;
			ia = new Character[a.length];
			for (int i = 0; i < a.length; i++) 
				ia[i]=a[i];				
		}
		else if(o instanceof byte[]){
			byte[] a = (byte[])o;
			ia = new Byte[a.length];
			for (int i = 0; i < a.length; i++) 
				ia[i]=a[i];
		}
		else
			throw new RuntimeException("type not recognized "+o.getClass());

		return ia;

	}

	/**
	 * @j2sNative
	 *  var s = typeof value;
	    if (s === 'object') {
	        if (value) {
	            if (typeof value.length === 'number' &&
	                    !(value.propertyIsEnumerable('length')) &&
	                    typeof value.splice === 'function') {
	                s = 'array';
	            }
	        } else {
	            s = 'null';
	        }
	    }
	    return s;
	 */
	public static String typeOf(Object value) {
		return null;
	}
	/** @j2sNative
	 *     var i, v;
	    if (typeOf(o) === 'object') {
	        for (i in o) {
	            v = o[i];
	            if (v !== undefined && typeOf(v) !== 'function') {
	                return false;
	            }
	        }
	    }
	    return true;
	 */
	public static boolean isEmpty(Object o) {
		return false;
	}
}
