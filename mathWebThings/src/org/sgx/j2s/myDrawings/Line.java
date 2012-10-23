package org.sgx.j2s.myDrawings;

import org.sgx.math.Jama.Matrix;

public class Line {
public Point p;
public double m;

public Line(Point p, double m) {
	super();
	this.p = p;
	this.m = m;
}
public Line(Point a, Point b) {
//	m=((double)MyMath.distInt(a.y, b.y))/((double)MyMath.distInt(a.x, b.x));
	m=(((double)a.y)-((double)b.y))/(((double)a.x)-((double)b.x));
	p=a;
}

public Point getOtherPoint() {
	/* let q be another point of this line which q.x=p.x+K, K>>0. then q.y=p.y+mK */
	return new Point(p.x+100, ((int)(p.y+m*100)) );
}

public Line(int x1, int y1, int x2, int y2) {
	this(new Point(x1,y1), new Point(x2,y2));
}

public boolean isPerpendicular(Line l) {
	return false;
}

/**
 * return A, B, C for this line Ax + By =C
 */
public double[] getCoefs1() {
return new double[]{m, -1.0, m*p.x-p.y};	
}


public Point intercept(Line l) {
	double [] M1 = getCoefs1(), M2 = l.getCoefs1();
	  Matrix A = new Matrix( new double[][] {{M1[0], M1[1]}, {M2[0], M2[1]}});
	   Matrix b = new Matrix(new double[] {M1[2], M2[2]}, 2);
	   double [][] x = A.solve(b).getArray();
		  return new Point(x[0][0], x[1][0]);
		  
}

}
