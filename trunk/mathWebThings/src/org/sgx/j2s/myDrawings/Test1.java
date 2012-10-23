package org.sgx.j2s.myDrawings;


public class Test1 {
	   public static void main (String argv[]) {
		   Line l1 = new Line(new Point(0,1), new Point(2,2));
		   Line l2 = new Line(new Point(2,1), new Point(1,2));
		   
		  System.out.println(l1.intercept(l2));
	   }
}
