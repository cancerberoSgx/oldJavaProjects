package org.sgx.j2s.fractals;

import org.sgx.j2s.bitmap.Bitmap;
import org.sgx.j2s.bitmap.BitmapFactory;
import org.sgx.j2s.bitmap.html.MyBitmap;
//import org.sgx.j2s.bitmap.html2.PureHtmlBitmap;
import org.sgx.j2s.myDrawings.Drawer;
import org.sgx.j2s.myDrawings.MyMath;
import org.sgx.j2s.myDrawings.Point;
import org.sgx.j2s.raphael.Paper;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.Color;

public class Julia {

	double a=-2.24, b=-0.65, c=0.43, d=-2.43;
	double factor=10.5, plus = 25.0;
	Color fg = Color.BLACK;
	boolean first=true;
	
	public Julia(double a, double b, double c, double d) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	public Julia() {
		// TODO Auto-generated constructor stub
	}
	
	public void drawIn(Bitmap b2, Point O, int iterac) {
		Point p = O;
		for (int i = 0; i < iterac; i++) {
			if(!first) {
				b2.paint(
					(int)Math.round(							
							p.x*factor + plus), 
					(int)Math.round(p.y*factor+ plus), fg);				
			}
			else
				first=false;
			
			p=getNext(p);
		}
	}
	Point getNext(Point p) {
		return new Point(
			(Math.sin(a*p.y)-Math.cos(b*p.x)),
			(Math.sin(c*p.x)-Math.cos(d*p.y))
		);
	}

public void changeBase() {
	first=true;
}
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
	public double getC() {
		return c;
	}
	public void setC(double c) {
		this.c = c;
	}
	public double getD() {
		return d;
	}
	public void setD(double d) {
		this.d = d;
	}
	public double getFactor() {
		return factor;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	public double getPlus() {
		return plus;
	}
	public void setPlus(double plus) {
		this.plus = plus;
	}

	
	
	//test
	
	
	/**
	 * al aumentar este valor se representará con mayor filedidad la el contexto del punto elegido. Debe estar entre 20 y 40. 
	 */
	private static final int POINT_COUNT=25;
	static final  int BITMAP_SIZE = 300;
	
	/** distancia entre los puntos de representación. si queremos que el alg demore siempre lo mismo al variar BITMAP_SIZE deberíamos dejar stepSize en función de BITMAP_SIZE, por ej BITMAP_SIZE/40 para hacer una cuadratura de 40x40 en la que representar al fractal*/
	static int stepSize = BITMAP_SIZE/25;
	
	
	/** cantidad de puntos a lo ancho y largo del lienzo sobre los que se hará la representación */ 
	private static final int M = BITMAP_SIZE/stepSize;
	
	public static void main(String[] args) {

		
		Julia j = new Julia();
		
		drawJulia(j);

	}
	public static void drawJulia(Julia j) {
		long timeCanvas = System.currentTimeMillis();
		

		System.out.println("drawing canvas");
		Bitmap b = BitmapFactory.getDefault(500,1,BITMAP_SIZE,BITMAP_SIZE);

		System.out.println("Bitmap finnished in "+timeCanvas+"ms");
		
		timeCanvas = System.currentTimeMillis()-timeCanvas;

		drawJulia(j,b);
	}




	public static void drawJulia(Julia j, Bitmap b) {

//		System.out.println("calculating and drawing julia");
		
		j.setPlus(BITMAP_SIZE/2);
		j.setFactor(BITMAP_SIZE/4);
		
//		long timeJulia = System.currentTimeMillis();
		
//		double step = BITMAP_SIZE/M;
		for(double kx=stepSize; kx<BITMAP_SIZE-stepSize; kx+=stepSize) {
			for(double ky=stepSize; ky<BITMAP_SIZE-stepSize; ky+=stepSize) {
				Point p=new Point(kx,ky);
//				System.out.println("new base: "+p);
				j.changeBase();
				j.drawIn(b, p, POINT_COUNT);
			}
		}
	
		
//		if(b instanceof MyBitmap) {
//			((MyBitmap)b)._gr.paint();
//		}
		b.flush();
//		timeJulia=System.currentTimeMillis()-timeJulia;
//		System.out.println("Julia fractal computed and drawed in "+timeJulia+"ms");
	}
}
