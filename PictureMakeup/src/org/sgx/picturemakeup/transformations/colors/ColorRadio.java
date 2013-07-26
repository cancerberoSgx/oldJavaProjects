package org.sgx.picturemakeup.transformations.colors;

import java.awt.Color;

public class ColorRadio {
	
	public int r,g,b,a;

	public ColorRadio(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public ColorRadio(int radio) {
		this(radio, radio, radio, radio);
	}
	/**
	 * @return true si y está en el intervalo x-d, x+d
	 */
	static boolean inInterval(int x,int d,int y) {
		if(y<=x+d && y>=x-d) return true;
		else return false;
	}
	/**	  
	 * @return true si x está en el radio this de c
	 */
	public boolean contains(Color c, Color x) {		
		if(inInterval(c.getRed(),r,x.getRed()) &&
			inInterval(c.getGreen(),g,x.getGreen()) &&
			inInterval(c.getBlue(),b,x.getBlue()) &&
			inInterval(c.getAlpha(),a,x.getAlpha()))
			return true;
		else return false;		
	}

}
