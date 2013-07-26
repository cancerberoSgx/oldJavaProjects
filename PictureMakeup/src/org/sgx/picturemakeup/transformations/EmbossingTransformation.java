package org.sgx.picturemakeup.transformations;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.LinkedList;
import java.util.List;

import org.sgx.picturemakeup.model.ImageTransformation;
import org.sgx.picturemakeup.model.NumericProperty;
import org.sgx.picturemakeup.model.Property;
import org.sgx.picturemakeup.utils.ImageFormat;
import org.sgx.utils.ImageUtils;




/**
 * emboss == realzado
 * @author sgx
 *
 */
public class EmbossingTransformation extends ImageTransformation {
	public static float FACTOR_MAX = 5.0f;
	public static float FACTOR_MIN = 0.0f;
	public static float FACTOR_DEFAULT = 1.0f;
	
	public static final int SIZE_MAX = 12;
	public static final int SIZE_MIN = 2;
	public static final int SIZE_DEFAULT = 3;
	
//	properties:
	public static final String FACTOR_PROP_ID = "factor",
		SIZE_PROP_ID = "size";
	static {
		BlurTransformation.props= new Property[] {
				new NumericProperty(FACTOR_PROP_ID, FACTOR_DEFAULT, FACTOR_MAX, FACTOR_MIN) 
//			new NumericProperty(SIZE_PROP_ID, SIZE_DEFAULT, SIZE_MAX, SIZE_MIN)
		};
	};
	
	//directions:
	public static final String DIRECTRION_VERTICAL = "vertical";
	public static final String DIRECTRION_HORIZONTAL = "Horizontal";
	public static final String DIRECTRION_NW2ES = "NW2ES";
	
	//attributes
	float factor=FACTOR_DEFAULT;
	int size=SIZE_DEFAULT;
	String direction = DIRECTRION_VERTICAL;
	int centerSize=1;
	Kernel kernel;	
	
	public EmbossingTransformation(float factor) {
		this.factor = factor;
	}
	
	public EmbossingTransformation() {
		this(FACTOR_DEFAULT);
	}

	Point[] getCenter(int size) {
		Point[] p= {new Point(size/2, size/2)};
		return p;
//		List<Point> l = new LinkedList<Point>();
//		l.add();
//		return l;
	}
	
//	void doKernel() {
//		float [] k = new float [size*size];
//		Point[] points=getCenter(size);
//		boolean center=false;
//		for(int i = 0; i<size; i++)
//			for(int j=0; j<size; j++) {
//				center=false;
//				for(int k=0;k<points.length;k++) {
//					if(new Point(i,j).equals(points[k])) {
//						center=true;
//						break;
//					}
//				}
//				if(center)
//				
//			}
//				k[i*size+j]=factor;
//		kernel = new Kernel(size,size,k);
//	}
	
	public BufferedImage applyTransformation(BufferedImage src) {
		src=ImageFormat.makeByteIndexed(src);
		//emboss kernel
		Kernel kernel = new Kernel(3, 3,
				new float[] {
				factor*-1, 0, 0,
				0, 1, 0,
				0, 0, factor});
		
//		Kernel kernel = new Kernel(5, 5,
//				new float[] {
//				factor*-1, 0,         0, 0,      0,
//				0,         (factor+factor)*-1, 0, 1,      0,
//				0, 0, 0,              factor*5, 0, 0,
//				0, 0, 0,                 (factor+factor), 0, 
//				0, 0, 0, 0,                       factor});
		
//		Kernel kernel = new Kernel(5, 5,
//		new float[] {
//		factor*-1, 0,         0, 0,      0,
//		0,         0, 0, 1,      0,
//		0, 0, 0,              factor, 0, 0,
//		0, 0, 0,                      0, 0, 
//		0, 0, 0, 0,                      factor});
		
		//fadeout
//		Kernel kernel = new Kernel(3, 3,
//				new float[] {
//					-1,-1,-1,
//					-1,factor,-1,
//					-1,-1,-1});
		
		//blur - factor entre
//		Kernel kernel = new Kernel(7, 7,
//				new float[] {
//				0.1111111f, 0.1111111f, 0.1111111f,
//				0.1111111f, 0.1111111f, 0.1111111f,
//				0.1111111f, 0.1111111f, 0.1111111f});
//				new float[] {
//				factor, factor, factor, factor, factor, factor, factor,
//				factor, factor, factor, factor, factor, factor, factor,
//				factor, factor, factor,factor, factor, factor, factor,
//				factor, factor, factor,factor, factor, factor, factor,
//				factor, factor, factor,factor, factor, factor, factor,
//				factor, factor, factor,factor, factor, factor, factor,
//				factor, factor, factor,factor, factor, factor, factor,});

//		Kernel kernel = new Kernel(3,3,
//				new float[] {
//				-1, 0, 1,
//				-4, 0, 4,
//				-1, 0, 1
//				});
		
		return ImageUtils.applyOp(src, new ConvolveOp(kernel));
	}

	public float getFactor() {
		return factor;
	}
	public void setFactor(Object value) {
		setFactor(castValueToFloat(value));
	}
	public void setFactor(float factor) {
		this.factor = factor;
	}
	public String shortDescription() {
		return "TODO";
	}

//	public void setSize(Object value) {
//		setSize(castValueToInt(value));
//	}
//	public void setSize(int x) {
//		size=x;
//		doKernel();
//	}
}


