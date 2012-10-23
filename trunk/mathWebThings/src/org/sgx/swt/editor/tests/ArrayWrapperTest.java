package org.sgx.swt.editor.tests;

import org.sgx.j2s.utils.Utils;
import org.sgx.swt.editor.basic.ArrayWrapper;

public class ArrayWrapperTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[][] s2 = new String[][]{{"1,1", "1,2", "1,3"}, {"2,1", "2,2", "2,3"}};
		ArrayWrapper w2 = new ArrayWrapper(s2, 2);
								
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				Utils.assertTrue(w2.getItem(i,j).equals((i+1)+","+(j+1)), "ArrayWrapperTest1 "+i+","+j + " - "+w2.getItem(i,j));
			}
		}
		w2.setItem(0,1,"hi");
		Utils.assertTrue(w2.getItem(0,1).equals("hi"), "ArrayWrapperTest2 t2");
		
		String[] s1 = new String[]{"1", "2", "3"};
//		ArrayWrapper w1 = new ArrayWrapper(s1);
//		Utils.assertTrue(w1.getItem(0).equals(w1.getItem(0,0)) && w1.getItem(0).equals("1"), "test 2 dim 2");
			
		System.out.println(Utils.printAssertsFailed());
	}

}
