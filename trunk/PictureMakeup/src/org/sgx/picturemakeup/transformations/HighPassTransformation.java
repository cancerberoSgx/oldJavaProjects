package org.sgx.picturemakeup.transformations;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import org.sgx.picturemakeup.model.ImageTransformation;
import org.sgx.picturemakeup.model.NumericProperty;
import org.sgx.picturemakeup.model.Property;
import org.sgx.picturemakeup.utils.ImageFormat;
import org.sgx.utils.ImageUtils;




/**
 * blur == falta de calidad
 * @author sgx
 *
 */
public class HighPassTransformation extends ImageTransformation {

	public static final float FACTOR_MAX = 1.0f;
	public static final float FACTOR_MIN = 0.0f;
	public static final float FACTOR_DEFAULT = 0.1111F;
	
	public static final int SIZE_MAX = 12;
	public static final int SIZE_MIN = 2;
	public static final int SIZE_DEFAULT = 3;

//	properties:
	public static final String FACTOR_PROP_ID = "factor",
		SIZE_PROP_ID = "size";
	static {
		BlurTransformation.props= new Property[] {
				new NumericProperty(FACTOR_PROP_ID, FACTOR_DEFAULT, FACTOR_MAX, FACTOR_MIN), 
			new NumericProperty(SIZE_PROP_ID, SIZE_DEFAULT, SIZE_MAX, SIZE_MIN)
		};
	};
	
	//<attribs
	float factor=FACTOR_DEFAULT;
	int size=SIZE_DEFAULT;
	Kernel kernel;
	
	
	public HighPassTransformation(float factor) {
		this.factor = factor;
	}
	
	public HighPassTransformation() {
		this(FACTOR_DEFAULT);
	}
	
	/**
	 * TODO
	 *
	 */
	void doKernel() {
		float [] k = new float [size*size];
		for(int i = 0; i<size; i++)
			for(int j=0; j<size; j++) 
				k[i*size+j]=factor;		
		kernel = new Kernel(size,size,k);
		
		//		laplacian kernel
//		kernel = new Kernel(3,3,new float[]{		
//				0,-1,0,
//				-1,4,-1,
//				0,-1,0
//		});
		
//		kernel = new Kernel(9,9,new float[]{		
//				0, 0, 3, 2, 2, 2, 3, 0, 0, 
//				0, 2, 3, 5, 5, 5, 3, 2, 0,
//				3, 3, 5, 3, 0, 3, 5, 3, 3,
//				2, 5, 3, -12, -23, -12, 3, 5, 2, 
//				2, 5, 0, -23, -40, -23, 0, 5, 2, 
//				2, 5, 3, -12, -23, -12, 3, 5, 2,
//				3, 3, 5, 3, 0, 3, 5, 3, 3,
//				0, 2, 3, 5, 5, 5, 3, 2, 0,
//				0, 0, 3, 2, 2, 2, 3, 0, 0
//		});
		
//		kernel = new Kernel(9,9,new float[]{		
//				0-1.0f, 1-1.0f, 1-1.0f, 2-1.0f, 2-1.0f, 2-1.0f, 1-1.0f, 1-1.0f, 0-1.0f, 
//				1-1.0f, 2-1.0f, 4-1.0f, 5-1.0f, 5-1.0f, 5-1.0f, 4-1.0f, 2-1.0f, 1-1.0f,
//				1-1.0f, 4-1.0f, 5-1.0f, 3-1.0f, 0-1.0f, 3-1.0f, 5-1.0f, 4-1.0f, 1-1.0f,
//				2-1.0f, 5-1.0f, 3-1.0f, -12-1.0f, -24-1.0f, -12-1.0f, 3-1.0f, 5-1.0f, 2-1.0f, 
//				2-1.0f, 5-1.0f, 0-1.0f, -24-1.0f, -40-1.0f, -24-1.0f, 0-1.0f, 5-1.0f, 2-1.0f, 
//				2-1.0f, 5-1.0f, 3-1.0f, -12-1.0f, -24-1.0f, -12-1.0f, 3-1.0f, 5-1.0f, 2-1.0f, 
//				1-1.0f, 4-1.0f, 5-1.0f, 3-1.0f, 0-1.0f, 3-1.0f, 5-1.0f, 4-1.0f, 1-1.0f,
//				1-1.0f, 2-1.0f, 4-1.0f, 5-1.0f, 5-1.0f, 5-1.0f, 4-1.0f, 2-1.0f, 1-1.0f,
//				0-1.0f, 1-1.0f, 1-1.0f, 2-1.0f, 2-1.0f, 2-1.0f, 1-1.0f, 1-1.0f, 0
//		});
//		kernel = new Kernel(3,3,new float[]{		
//				0.12601699f, 1.1609108f, 0.12601699f, 
//				1.1609108f, -12.433979f, 1.1609108f, 
//				0.12601699f, 1.1609108f, 0.12601699f, 
//		});
	
	}

	public BufferedImage applyTransformation(BufferedImage src) {
		src=ImageFormat.makeByteIndexed(src);
		return ImageUtils.applyOp(src, new ConvolveOp(kernel));
	}

	public void setFactor(float f) {
		factor=f;
		doKernel();
	}

	public String shortDescription() {
		return "TODO";
	}

	public void setFactor(Object value) {
		try {
			float f = castValueToFloat(value);
			setFactor(f);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("unble to parse float");
		}
		
	}
	public void setSize(Object value) {
		setSize_(castValueToInt(value));
	}
	public void setSize_(int x) {
		size=x;
		doKernel();
	}
}
