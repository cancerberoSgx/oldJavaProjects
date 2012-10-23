package org.sgx.swing.j2s.model.base;

public class Point extends Magnitude {
	
	public int x, y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point plus(Point p) {
		return new Point(x+p.x, y+p.y);
	}
	public Point negate() {
		return new Point(-x, -y);
	}
	public boolean equals(Point p) {
		return x==p.x && y==p.y;
	}
	
	public Point clone() {
		return new Point(x,y);
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
