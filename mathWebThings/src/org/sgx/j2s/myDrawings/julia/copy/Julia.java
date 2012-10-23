package org.sgx.j2s.myDrawings.julia.copy;

import org.sgx.j2s.myDrawings.Drawer;
import org.sgx.j2s.myDrawings.Point;
import org.sgx.j2s.raphael.Paper;

public class Julia {

	double a=-2.24, b=-0.65, c=0.43, d=-2.43;
	double factor=10.5, plus = 100.0;
	
	public void drawIn(Drawer d, Point O, int iterac) {
		Point p = O;
		for (int i = 0; i < iterac; i++) {
//			System.out.println(p.toString());
			Point p1 = new Point(p.x*factor + plus, p.y*factor+ plus);
			d.drawPoint(p1);
			
			p=getNext(p);
		}
	}
	Point getNext(Point p) {
		return new Point(
			(Math.sin(a*p.y)-Math.cos(b*p.x)),
			(Math.sin(c*p.x)-Math.cos(d*p.y))
		);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Paper p = new Paper(400,1,800,800);
		Drawer drawer = new Drawer(p);
		Julia j1 = new Julia();
		j1.drawIn(drawer, new Point(1,2), 5000);
	}

}
