package org.sgx.pickturemakeup.transformations;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import org.sgx.pickturemakeup.model.ImageTransformation;
import org.sgx.pickturemakeup.model.NumericProperty;
import org.sgx.pickturemakeup.model.Property;
import org.sgx.pickturemakeup.utils.ImageFormat;
import org.sgx.utils.ImageUtils;




/**
 * las mastrices de esta trans son de tipo 
 * 	1, 1, 1, 1, 1,
	1, 0, 0, 0, 1,
	1, 0, 0, 0, 1,
	1, 0, 0, 0, 1,
	1, 1, 1, 1, 1,
	Y QUEDAN LINDAS
	
	(FBlur)
 * @author sgx
 *
 */
public class FTransformation  extends ImageTransformation{

	public static final int SIZE_DEFAULT = 5;
	public static final int SIZE_MAX = 15;
	public static final  int SIZE_MIN = 2;
	
	public static final float POWER_DEFAULT = 1;
	public static final float POWER_MAX = 1.9f;
	public static final float POWER_MIN = 0.1f;
	
	//properties:
	public static final String POWER_PROP_ID = "factor",
		SIZE_PROP_ID = "size";
	static {
		props= new Property[] {
			new NumericProperty(POWER_PROP_ID, POWER_DEFAULT, POWER_MAX, POWER_MIN), 
			new NumericProperty(SIZE_PROP_ID, SIZE_DEFAULT, SIZE_MAX, SIZE_MIN)
		};
	}
	
	//<attribs
	float power=POWER_DEFAULT;
	int size =SIZE_DEFAULT;
	
	Kernel kernel;	
	
	public FTransformation(int level) {
		doKernelFor(level);
	}
	public FTransformation() {
		this(SIZE_DEFAULT);
	}
	
	
	void doKernelFor(int l) {
		float [] k = new float [l*l];
		for(int i = 0; i<l; i++)
			for(int j=0; j<l; j++) 
				if(i==0 || j==0 || i==l-1 || j==l-1)
					k[i*l+j]=power;
				else k[i*l+j]=0; 
		kernel = new Kernel(l,l,k);
	}

	public BufferedImage applyTransformation(BufferedImage src) {
		src=ImageFormat.makeByteIndexed(src);
		return ImageUtils.applyOp(src, new ConvolveOp(kernel));
	}
	public void setSize(Object p) {
		setFactor(castValueToInt(p));
	}
	public void setSize(int x) {
		this.size=x;
		doKernelFor(x);
	}
	public void setFactor(Object p) {
		setFactor(castValueToFloat(p));
	}
	public void setFactor(float p) {
		this.power=p;
		doKernelFor(size);
	}
	public String shortDescription() {
		return "TODO";
	}
}