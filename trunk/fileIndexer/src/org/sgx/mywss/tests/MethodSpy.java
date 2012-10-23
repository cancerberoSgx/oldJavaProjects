package org.sgx.mywss.tests;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import static java.lang.System.out;

public class MethodSpy {
	private static final String  fmt = "%24s: %s%n";

	// for the morbidly curious
	<E extends RuntimeException> void genericThrow() throws E {}

	String[] getArray(){return null;}
	String getString(){return null;}
	void voidmethod(){}
	public static void main(String... args) {
		try {
			Class<?> c = Class.forName(MethodSpy.class.getName());
			Method[] allMethods = c.getDeclaredMethods();
			for (Method m : allMethods) {
//				if (!m.getName().equals("voidmethod")) {
//					continue;
//				}
//				out.println("\n "+(isPublicMethod(m)?" public ": " "));
				out.format("%s%n", m.toGenericString());

				out.format(fmt, "ReturnType", m.getReturnType());
				out.format(fmt, "GenericReturnType", m.getGenericReturnType());

				Class<?>[] pType  = m.getParameterTypes();
				Type[] gpType = m.getGenericParameterTypes();
				for (int i = 0; i < pType.length; i++) {
					out.format(fmt,"ParameterType", pType[i]);
					out.format(fmt,"GenericParameterType", gpType[i]);
				}

				Class<?>[] xType  = m.getExceptionTypes();
				Type[] gxType = m.getGenericExceptionTypes();
				for (int i = 0; i < xType.length; i++) {
					out.format(fmt,"ExceptionType", xType[i]);
					out.format(fmt,"GenericExceptionType", gxType[i]);
				}
			}

			// production code should handle these exceptions more gracefully
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		}
	}
	
	
}