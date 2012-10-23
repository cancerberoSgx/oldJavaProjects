package org.sgx.j2s.util.impl.test;

import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.util.impl.LWMap;
import org.sgx.j2s.util.impl.Utils;



/**
 * @author SGURIN
 */
public class LWMapTest {
	
	public void test1() {
		LWMap<Bean1, Bean1> m = new LWMap<Bean1, Bean1>();
		Bean1 b1 = new Bean1("uno", 1.1), b2 = new Bean1("dos", 2.2), b3 = new Bean1("tres", 3.3);
		Bean1[]bs={b1,b2,b3};
		for(int i=0;i<bs.length;i++){
			if(i!=bs.length-1)
				m.put(bs[i],bs[i+1]);
			else
				m.put(bs[i],bs[0]);
		}
		for(int i=0;i<bs.length;i++){
			if(i!=bs.length-1)
				Utils.assertTrue(m.get(bs[i]).equals(bs[i+1]), "1");
			else
				Utils.assertTrue(m.get(bs[i]).equals(bs[0]), "2");
		}
		
	}
	public void test2() {
		int N = 20;
		
		LWMap<String, String> m = new LWMap<String, String>();
		
		String[] keys = new String[N];
		String[] vals = new String[N];
		for(int i=0; i<N; i++) {
			keys[i]="_key"+i+"_key";
			vals[i]="_val"+i+"_val";
		}
		//put
		Utils.assertTrue(m.keys().size()==0, "LinkedListTest:test1:assert1");
		for(int i=0;i<keys.length;i++)
			m.put(keys[i], vals[i]);
		
		Utils.assertTrue(m.keys().size()==keys.length, "LinkedListTest:test1:assert2");
		
		for(int i=0;i<keys.length;i++)
			Utils.assertTrue(m.containsKey(keys[i]) && !m.containsKey("imposibleeee"), 
					"LinkedListTest:test1:assert3_"+i);
		
		
	}
	public static void main( String [ ] args )	    {
		JsUtils.activateJ2sDebug();
		System.out.println("fin");
		Utils.cleanTests();
		new LWMapTest().testAll();
	}

	public  void testAll() {
		test2();
		test1();
		System.out.println(Utils.printAssertsFailed());
	}
}
