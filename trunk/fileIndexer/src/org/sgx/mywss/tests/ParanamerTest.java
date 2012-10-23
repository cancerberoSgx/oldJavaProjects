package org.sgx.mywss.tests;

import java.lang.reflect.Method;

import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;

public class ParanamerTest {


	  private static final String __PARANAMER_DATA = "v1.0 \n"
	        + "<init> \n"
	        + "<init> org.sgx.mywss.tests.ParanamerTest \n"
	        + "main java.lang.String[] args \n";
	 
	 public static void a(String a){
		 
	 }
	public static void main(String[] args) {
		        
		 Method method = ParanamerTest.class.getMethods()[0];

		 Paranamer paranamer = new CachingParanamer();
		 
		 String[] parameterNames = paranamer.lookupParameterNames(method);
		 
		 System.out.print(parameterNames[0]);
	}

}
