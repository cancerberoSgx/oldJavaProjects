package org.sgx.utils.tests;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.sgx.utils.JavaBeansUtils;
import org.sgx.utils.JavabeanFormatException;
import org.sgx.utils.ObjectUtils;


import junit.framework.TestCase;

public class JavaBeanUtilsTest extends TestCase  {

	public static void testjavabeansyhings() throws IllegalArgumentException, JavabeanFormatException, IllegalAccessException, InvocationTargetException {
		JavaUtilsTestBean bean = new JavaUtilsTestBean("hola", 3);
		
		Collection<String> props = JavaBeansUtils.getProperties(bean);
		assertTrue(props.size()==2);
		
		Object p1Val = JavaBeansUtils.getProperty(bean, "prop1");
		assertTrue((p1Val instanceof String) && ((String)p1Val).equals("hola"));
		
		JavaBeansUtils.setProperty(bean, "prop1", "como anda");
		Object p1Val2 = JavaBeansUtils.getProperty(bean, "prop1");
		assertTrue((p1Val2 instanceof String) && ((String)p1Val2).equals("como anda"));
		
		
//		System.out.println(org.sgx.utils.toString(ObjectUtils.transformObjectToMap((bean))));
	}
	
	public static void main(String[] args)  {
		;
		try {
			System.out.println(JavaBeansUtils.printBean( 
					new  ObjectUtilsTestBean("uno",1,1.0,true)));
			
			ObjectUtilsTestBean b = (ObjectUtilsTestBean) ObjectUtils.transformArrToBean_(
					new Object[][]	{
							{"foo", "cero"},
							{"integer", 0},
							{"real", 0.0}, 
							{"bool1", false}
					}, 
						ObjectUtilsTestBean.class);
			System.out.println(JavaBeansUtils.dumpBean(b));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavabeanFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
