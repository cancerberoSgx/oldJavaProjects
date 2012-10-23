package org.sgx.j2s.fractals;

import org.sgx.j2s.bitmap.Bitmap;
import org.sgx.j2s.bitmap.BitmapFactory;
import org.sgx.j2s.math.complex.Complex;
import org.sgx.j2s.widget.base.Color;

public class MandelDrawer {
	
	private static final  int BITMAP_SIZE = 300;
	
	public static void main(String[] args) {
//		mandel1Alg();
		mandel2Alg();

	}

//	private static void mandel2Alg() {
//		Bitmap b = BitmapFactory.getDefault(90,90, BITMAP_SIZE, BITMAP_SIZE);
//		MandelBrot2 m2 = new MandelBrot2();
//		for (int i = 0; i < BITMAP_SIZE; i++) {
//			for (int j = 0; j < BITMAP_SIZE; j++) {
//				int m = m2.doMandel	(i,j);
//				if(m==0)
//					b.paint(i,j,Color.BLACK);
//
//			}
//		}
//	}
	private static void mandel2Alg() {
		MandelBrot2__ fractal = new MandelBrot2__(new Complex(0.2,-0.1));
		Bitmap b = BitmapFactory.getDefault(200,200, BITMAP_SIZE, BITMAP_SIZE);
		
		double TOP=1.5, DOWN=-1.5, L = Math.abs(TOP-DOWN);

		double step=(L/BITMAP_SIZE)*2;		
		for (double x = DOWN; x < TOP; x+=step) {
			for (double y = DOWN; y <TOP; y+=step) {
//				Complex c = new Complex(x,y);
				int c_mandel = fractal.doMandel(new Complex(x,y));
				if(c_mandel==0) {
					int x_ = (int) ( ((BITMAP_SIZE*x) + (Math.abs(DOWN)*BITMAP_SIZE)) / L ),
						y_= (int) ( ((BITMAP_SIZE*y) + (Math.abs(DOWN)*BITMAP_SIZE)) / L );
					 b.paint(x_, y_, Color.BLACK);
				}
			}
		}
	}
	private static void mandel1Alg() {
		MandelBrot fractal = new MandelBrot();
		Bitmap b = BitmapFactory.getDefault(90,90, BITMAP_SIZE, BITMAP_SIZE);
		
		double step=0.01;		
		double TOP=1.0, DOWN=-2, L = TOP-DOWN;
		
		for (double x = DOWN; x < TOP; x+=step) {
			for (double y = DOWN; y <TOP; y+=step) {
				Complex c = new Complex(x,y);
				int c_mandel = fractal.doMandel(c);
				if(c_mandel==0) {
					int x_ = (int) ( ((BITMAP_SIZE*x) - (DOWN*BITMAP_SIZE))/L ),
						y_= (int) ( ((BITMAP_SIZE*y) - (DOWN*BITMAP_SIZE))/L );
					 b.paint(x_, y_, Color.BLACK);
				}
			}
		}
	}

}
