package org.sgx.j2s.html.dom.util;

import org.sgx.j2s.html.dom.Element;
import org.sgx.j2s.yui.BenchMaker;

public class BunchTests {
	static int n = 100;
	
	public static void createBunchJsTest(boolean js) {		
		Element e = DomUtils.createElement("div", DomUtils.getBody());
		if(js) 
			DomUtils.createBunchJs("ja", "div", e, n);
		else  
			DomUtils.createBunchJava("ja", "div", e, n);
		if(e.childNodes.length!=n)
			System.out.println("Error1");		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BenchMaker b = new BenchMaker();
		long java = b.readDuration(new Runnable() {
			public void run() {
				createBunchJsTest(false);
			}			
		}), js = b.readDuration(new Runnable() {
			public void run() {
				createBunchJsTest(true);
			}
		});
		
		System.out.println("string concatenation bench: \njs native: "+
				js+"\njava code translated: "+java);
		System.out.println("today: the times are the same. congratullations j2s author!");
				
		System.out.println("finnish");
	}

}
