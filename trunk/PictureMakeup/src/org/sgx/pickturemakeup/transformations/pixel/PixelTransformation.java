package org.sgx.pickturemakeup.transformations.pixel;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import org.sgx.pickturemakeup.model.ImageTransformation;

/**
 *  a manopla negro!
 *  
 * incluyo facilidades para el registro de varias implementaciones 
 * de PixelTransformation
 * 
 * @author sgx
 *
 */
public abstract class PixelTransformation  extends ImageTransformation{

	public abstract Color getPixelTransf(Pixel p, Image img);
	
	public static Color buildColor(int argb) {
		int alpha = (argb >> 24) & 0xff;
		int red = (argb >> 16) & 0xff;
		int green = (argb >> 8) & 0xff;
		int blue = argb & 0xff;
		return new Color(red,green,blue,alpha);
	}
	public static int buildColor(Color c) {
		int a = (c.getAlpha()<<24) & 0xff000000;
		int r = (c.getRed()<<16) & 0x00ff0000;
		int g = (c.getGreen()<<8) & 0x0000ff00;
		int b = (c.getBlue()) & 0x000000ff;
		return a|r|g|b;
	}
	
	public BufferedImage applyTransformation(BufferedImage src) {		
		int W = src.getWidth(), H = src.getHeight();
		BufferedImage dest = new BufferedImage(W,H,BufferedImage.TYPE_INT_ARGB);
		for(int i=0; i<W;i++) {
			for(int j = 0; j<H;j++) {
				//TODO: eficienzar esto !!!
				Color c = buildColor(src.getRGB(i,j));
				Color newC = getPixelTransf(new Pixel(i,j,c),src);
				int buildColor = buildColor(newC);
				dest.setRGB(i,j,buildColor);
			}
		}
		return dest;
	}

	public abstract String shortDescription();

}
