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
public class BlurTransformation extends ImageTransformation {

	public static final float FACTOR_MAX = 0.5f;
	public static final float FACTOR_MIN = 0.0f;
	public static final float FACTOR_DEFAULT = 0.1111F;
	
	public static final int SIZE_MAX = 12;
	public static final int SIZE_MIN = 2;
	public static final int SIZE_DEFAULT = 3;
	
	//properties:
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
	
	
	public BlurTransformation(float factor) {
		this.factor = factor;
	}
	
	public BlurTransformation() {
		this(FACTOR_DEFAULT);
	}
	
	void doKernel() {
		float [] k = new float [size*size];
		for(int i = 0; i<size; i++)
			for(int j=0; j<size; j++) 
				k[i*size+j]=factor;
		kernel = new Kernel(size,size,k);
	}

	public BufferedImage applyTransformation(BufferedImage src) {
		src=ImageFormat.makeByteIndexed(src);		
		return ImageUtils.applyOp(src, new ConvolveOp(kernel));
		
	}

	
	public void setFactor(float f) {
		factor=f;
		doKernel();
	}
	public void setFactor(Object value) {
		setFactor(castValueToFloat(value));
	}
	public void setSize(Object value) {
		setSize(castValueToInt(value));
	}
	public void setSize(int x) {
		size=x;
		doKernel();
	}

	public String shortDescription() {
		return "TODO";
	}

//	
//	public void changePropertyValue(String propId, Object value) {
//		if(propId.equals(FACTOR_PROP_ID))			
//			this.setFactor(this.castValueToFloat(value));
//		else if(propId.equals(SIZE_PROP_ID)) 
//			this.setsize(castValueToInt(value));
//		else
//			throw new RuntimeException("Property id not recognized");
//		this.notifyAll(propId, value);
//	}
//
//	public Object getPropertyValue(String propertyId) {
//		if(propertyId.equals(SIZE_PROP_ID)) 
//			return size;
//		else if(propertyId.equals(FACTOR_PROP_ID)) 
//			return factor;
//		else
//			throw new RuntimeException("Property id not recognized");
//	}
//
//	public Property[] getProperties() {
//		return props;
//	}


}
 