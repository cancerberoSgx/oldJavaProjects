package org.sgx.mywss.tests.pojomethod;

import java.util.List;
import java.util.Map;

import org.sgx.mywss.Home;
import org.sgx.mywss.model.POJOMessageListener;

public class PojoMethodTest {

	public static void main(String[] args) {
		try {
			test1();
			test2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private static void test2() {
//		Pojo1 p = new Pojo1();
//		p.getClass().getTypeParameters().length;
		
	}
/**for invokeing Pojo1 
 *  List<String> method1(String p1, List p2, Map<String, Pojo1> p3) method use:
 *  
 *  http://localhost/fileIndexer/MYWS?endpoint=pojo1_endpoint&msg={"name":"method1", "params":{"attr1":"hi", "attr2":["llo"], "attr3":{}}}
 *  http://localhost/fileIndexer/MYWS?endpoint=pojo1_endpoint&msg={"name":"method2", "params":{"attr1":"hi", "attr2":["llo"], "attr3":{}}}
 *  
 * http://localhost:8080/fileIndexer/MYWS?endpoint=pojo1_endpoint&msg={"name":"method1", "params":{"p1":"hi", "p2":["llo"], "p3":{}}}
 */
	private static void test1() throws Exception {
		Pojo1 pojo1 = new Pojo1();
		Home.getInstance().registerPojoMessageListener("pojo1_endpoint", pojo1);
	}

}
