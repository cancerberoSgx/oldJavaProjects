package org.sgx.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * simple, portable (j2script), dirty and hightlevel javabeans operations  
 *   
 * @author sgurin
 *
 */
public class JavaBeansUtils {
	public static Collection<String> getProperties(Object b) {
		List<String> props = new LinkedList<String>();
		Method[] M = b.getClass().getMethods();
		List<Method> getters = (List<Method>) JavaUtils.select(M, new Selector<Method>() {
			public boolean select(Method o) {
				return o.getName().startsWith("get")||o.getName().startsWith("is");
			}
		});
		List<Method> setters = (List<Method>) JavaUtils.select(M, new Selector<Method>() {
			public boolean select(Method o) {
				return o.getName().startsWith("set");
			}
		});
		for(Method m : getters) {
			String name = m.getName();
			if(name.length()>3) {
				String pName = name.substring(3, name.length());
				final String sName = "set"+pName;
				if(JavaUtils.exists(setters, new Selector<Method>() {
						public boolean select(Method o) {
							return o.getName().equals(sName);
						}					
					})) {					
					props.add(pName.substring(0,1).toLowerCase()+pName.substring(1, pName.length()));
				}
			}
		}
		return props;
	}
	
	public static Object getProperty(Object b, final String pName) throws JavabeanFormatException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		final String pName_ = pName.substring(0,1).toUpperCase()+pName.substring(1, pName.length());
		Collection pgetters = JavaUtils.select(b.getClass().getMethods(), new Selector<Method>() {
			public boolean select(Method o) {
				//TODO: check o.getModifiers
				return (o.getName().equals("get"+pName_)||o.getName().equals("is"+pName_)) && 
					(o.getTypeParameters()==null || o.getTypeParameters().length==0);
			}
		});
		if(pgetters.size()!=1)
			throw new JavabeanFormatException("there is no unique getter for property named "+ pName+" ");
		Method getter = (Method) pgetters.iterator().next();
		return getter.invoke(b, new Object[0]);		
	}
	public static Object getProperty_(Object b, final String pName){
		try {
			return getProperty(b,pName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setProperty(Object b, final String pName, Object val) throws JavabeanFormatException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		final String pName_ = pName.substring(0,1).toUpperCase()+pName.substring(1, pName.length());
		Collection psetters = JavaUtils.select(b.getClass().getMethods(), new Selector<Method>() {
			public boolean select(Method o) {
				//TODO: check o.getModifiers y que el return type sea void
				return o.getName().equals("set"+pName_) && 
				o.getParameterTypes().length==1 &&
				//					o.getReturnType() es void  &&
				true;
			}
		});
		if(psetters.size()!=1)
			throw new JavabeanFormatException("there is no unique setter for property named "+ pName+" ");
		Method setter = (Method) psetters.iterator().next();
		Object[] args ={val}; 
		TypeVariable<Method>[] tp = setter.getTypeParameters();
		setter.invoke(b, args);		
	}
	public static void setProperty_(Object b, final String pName, Object val){
		try {
			 setProperty(b,pName,val);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//misc utils	
	public static String printBean(Object b) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, JavabeanFormatException {
		Collection<String> props = getProperties(b);
		String s = "{";
		for(String p : props) {
			Object val = getProperty(b, p);
			s+=p+":"+val+", ";//"("+val.getClass().getName()+"),";
		}
		return s+"}";
	}
	public static String printBean_(Object b) {
		try {
			return printBean(b);
		} catch (Exception e) {
			return null;
		}
	}
	public static String dumpBean(Object b){
		try {
			return printBean(b);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
