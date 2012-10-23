package org.sgx.utils.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

//@AnnotationTest {
//	field1 = "hola mundo"
//}

public class AnnotationUtils {

	/**
	 * tool for extracting annotations from a method, field, or class definition
	 * 
	 * 
	 * @param member
	 * @return
	 */
	public static OrderedMap<String, String> getAnnotationsFrom(AnnotatedElement member) {
		Annotation[] annotations = member.getAnnotations();
//		annotations[0].
		return null;
	}
	
	
	private void testMethod() {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		Method m = String.class.getMethod(null, null);
		
	}
	
}
