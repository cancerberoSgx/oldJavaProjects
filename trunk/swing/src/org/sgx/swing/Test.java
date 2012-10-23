package org.sgx.swing;

import java.util.Iterator;
import java.util.Properties;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties props = System.getProperties();
		Iterator<Object> i = props.keySet().iterator();
		while (i.hasNext()) {
			String k= (String ) i.next();
			System.out.println(k+" : "+props.getProperty(k));
			
		}
	}

}
