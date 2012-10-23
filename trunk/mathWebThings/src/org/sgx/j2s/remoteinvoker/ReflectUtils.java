package org.sgx.j2s.remoteinvoker;

import java.lang.reflect.Method;
//import java.lang.reflect.TypeVariable;
import java.util.Map;

public class ReflectUtils {
	public void invokeStaticMethod(Class c, String methodName, Map<String, Object>params) {
		Method[] mts = c.getMethods();
		for (int i = 0; i < mts.length; i++) {
			Method m = mts[i];
//			if(m.getName().equals(methodName) && m.getModifiers()){
//				
//			}
		}
//		ModelHelper.
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReflectUtils u = new ReflectUtils();
		System.out.println(u.listMethods(ReflectUtils.class));
	}
private static void privateStatic(){}

	//debug
	public String listMethods (Class c) {
		String s = "methods in class "+c+"\n";
		Method[] mts = c.getMethods();
//		for (int i = 0; i < mts.length; i++) {
//			Method m = mts[i];
////			s+=m.getModifiers() +" "+ m.getName()+"(";
//			s+= m.getName()+"(";
//			TypeVariable<Method>[] tps = m.getTypeParameters();
////			for (int j = 0; j < tps.length; j++) {
////				TypeVariable<Method> tp = tps[i];
////				tp.getName()+":"+tp.
////			}
//			s+=")\n";
//		}
		return s;
	}

	
}
