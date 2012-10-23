package org.sgx.math.parser;

import java.util.Vector;

/**
 * 
 * @author SGURIN
 *
 */
public class Test {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		 Variable v = new Variable('x');
		 v.setValue(4);
		 Vector<Variable> vec = new Vector<Variable>();
		 vec.addElement(v);
		 Function f = new Function("((x^2-3))*((x-5))+5");
		 double result = f.calculate(vec);
		 System.out.println(result);
	}

}
