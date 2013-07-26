package org.sgx.picturemakeup.transformations.pixel;


import java.awt.Color;
import java.awt.Image;

import org.sgx.picturemakeup.gui.ImageWidgetImpl;

public class DitherColor extends DitherTransformation{
	public static final Color colorDefault =Color.WHITE;
	
	Color obj=Color.WHITE;

	@Override
	public Color getDitherOposite(Pixel p, Image img) {
		return obj;
	}

	@Override
	public String shortDescription() {
		return "";
	}

	public DitherColor(float alpha, float blue, float green, float red, float alpha2, float blue2, float green2, float red2, Color obj) {
		super(alpha, blue, green, red, alpha2, blue2, green2, red2);
		this.obj = obj;
	}
	public DitherColor( Color obj) {
		super();
		this.obj = obj;
	}
	public DitherColor( ) {
		this(colorDefault);
	}
	
//	public static void main(String [] a) {
//		ImageWidgetImpl w = new ImageWidgetImpl(ImageWidgetImpl.defaultImgPath);
//		Image img = w.getBufferredImage();
//		DitherColor t = new DitherColor();
//		t.setLevelAlpha(1.0);
//		t.setLevelRed(1.0);
//		t.setLevelGreen(1.0);
//		Color in=new Color(123,123,233);
//		Color c = t.interpolate(new Pixel(0,0,in),img);
//		System.out.println(in+" - "+c);
//	}
}
