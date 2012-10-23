package org.sgx.j2s.myDrawings;

public class LineSegment extends Line {
Point q;
	public LineSegment(Point a, Point b) {
		super(a, b);
		this.p=a;
		this.q=b;
	}

	public Point middle() {
		return new Point((p.x+q.x)/2, (p.y+q.y)/2);
	}
}
