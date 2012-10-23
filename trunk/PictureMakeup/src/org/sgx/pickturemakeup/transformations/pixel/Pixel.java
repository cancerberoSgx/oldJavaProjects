package org.sgx.pickturemakeup.transformations.pixel;

import java.awt.Color;

public class Pixel {

	public int x,y;
	public Color c;
	public Color getC() {
		return c;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Pixel(int x, int y, Color c ) {
		super();
		// TODO Auto-generated constructor stub
		this.c = c;
		this.x = x;
		this.y = y;
	}
}
