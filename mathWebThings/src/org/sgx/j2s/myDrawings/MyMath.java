package org.sgx.j2s.myDrawings;

public class MyMath {
	public static int distInt(int a, int b) {
		return a>b?(a-b):(b-a);
	}
	public static double distDouble(double a, double b) {
		return a>b?(a-b):(b-a);
	}
	
	

/**
>I'm looking for some sort of formulae or algorithm that will, when given three
>points on a cartesian plane, find the centre and radius of the circle that passes
>through those points?

Each point (x,y) is expected to satisfy the same equation  
	x^2+y^2 + A x + B y + C = 0
so plug in the three combinations of  x  and  y  to get three equations in
the three unknowns  A, B, and C. Solve (assuming the points are not
collinear). Writing the equation as
	(x + A/2)^2 + (y + B/2)^2 = (A^2+B^2)/4 - C
we see the center is at  (-A/2, -B/2)  and the  radius is  sqrt(A^2/4+B^2/4-C).
 */
	
//	public static Circle circlePass3Points(Point a, Point b, Point c) {
//		return null;		
//	}
//	
//	public static Circle circlePass3Points(Point a, Point b, Point c) {
//		return null;		
//	}
}
