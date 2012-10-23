package org.sgx.webMatrix.tests;

import org.sgx.math.Jama.Matrix;
import org.sgx.webMatrix.logic.Logic;

public class LogicTest {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
//		double[][] A_ = new double[][]{{3,-2},{4,8}};
//		double[][] B_ = new double[][]{{2}, {24}}; //TIENE QUE DAR X={2,2}
		
		double[][] A_ = new double[][]{{4,3},{2,5}};
		double[][] B_ = new double[][]{{22}, {18}}; //TIENE QUE DAR X={4,2}
		
		Matrix A = Logic.toMatrix(A_), B=Logic.toMatrix(B_);

		 Matrix x = A.solve(B);
		 
		System.out.println("A : \n"+A+"\n\nB: \n"+B+"\n\nx: \n"+x);
//		Object[] results = Logic.solveSystem(A,B);
	}

}
