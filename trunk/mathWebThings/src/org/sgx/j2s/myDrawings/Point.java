package org.sgx.j2s.myDrawings;

public class Point {


public double x, y;

public Point(double x, double y) {
	super();
	this.x = x;
	this.y = y;
}

public String toString() {
	return "("+x+","+y+")";
}
}
