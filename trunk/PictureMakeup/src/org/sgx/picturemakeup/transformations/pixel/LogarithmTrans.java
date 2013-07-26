package org.sgx.picturemakeup.transformations.pixel;

import java.awt.Color;
import java.awt.Image;

import org.sgx.picturemakeup.test.TestImageTransformation;


public class LogarithmTrans extends PixelTransformation {

	float scaling=24.5f;
	
	@Override
	public Color getPixelTransf(Pixel p, Image img) {
		return log1Color(p.c, scaling);
	}
	public static Color log1Color(Color c, float mult) {
		return new Color((int)(mult*Math.log1p(c.getRed())), 
				(int)(mult*Math.log1p(c.getGreen())),
				(int)(mult*Math.log1p(c.getBlue())),
				(int)(c.getAlpha()));
	}
	@Override
	public String shortDescription() {
		return "loga";
	}
	
	public static void main(String [] a) {
		LogarithmTrans t = new LogarithmTrans();	
		new TestImageTransformation(t).run();
	}

}
