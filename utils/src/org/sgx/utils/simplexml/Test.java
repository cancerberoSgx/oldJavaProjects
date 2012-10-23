package org.sgx.utils.simplexml;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DocHandler doc = new Reporter();
		  try {
			QDParser.parse(doc,new FileReader("resources/test/config.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
