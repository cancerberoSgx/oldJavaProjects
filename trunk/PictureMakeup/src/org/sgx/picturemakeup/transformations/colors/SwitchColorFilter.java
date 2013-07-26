package org.sgx.picturemakeup.transformations.colors;

import java.awt.Color;
import java.awt.image.RGBImageFilter;


public class SwitchColorFilter extends RGBImageFilter {

	ColorRadio cr;
	Color c;
	Color colorNew;
	int cn;
	
	public SwitchColorFilter(Color c, ColorRadio cr, Color colorNew) {
		canFilterIndexColorModel = false;
		this.cr=cr;
		this.c=c;
		this.colorNew=colorNew;
		this.cn=buildColor(colorNew);
	}

	public static Color buildColor(int argb) {
		int alpha = (argb >> 24) & 0xff;
		int red = (argb >> 16) & 0xff;
		int green = (argb >> 8) & 0xff;
		int blue = argb & 0xff;
		return new Color(red,green,blue,alpha);
	}
	public static int buildColor(Color c) {
		int a = (c.getAlpha()<<24) & 0xff000000;
		int r = (c.getAlpha()<<16) & 0x00ff0000;
		int g = (c.getAlpha()<<8) & 0x0000ff00;
		int b = (c.getAlpha()) & 0x000000ff;
		return a|r|g|b;
	}
	// This method is called for every pixel in the image
	public int filterRGB(int x, int y, int rgb) {
		if(cr.contains(c, buildColor(rgb)))
			return cn;
		else return rgb;
	}

}