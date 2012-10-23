package org.sgx.j2s.raphael;

public class Color {

	public static final Color BLACK = new Color(0,0,0);
	public static Color WHITE = new Color(255,255,255);
	public static Color RED = new Color(255,0,0);
	public static Color GREEN = new Color(2,255,2);
	public static Color BLUE = new Color(0,0,255);
	
	public int red, green, blue;

	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public String toString() {
		return toCSS();
	}
	public String toCSS() {
		return "rgb("+red+","+green+","+blue+")";
	}
	public Color clone() {
		return new Color(red, green,blue);
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}
}
