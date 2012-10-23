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
 * gauss smoothing
 * @author sgx
 *
 */
public class GaussianSmoothTransformation extends ImageTransformation {

	public static final float DEVIATION_MAX = 5.0f;
	public static final float DEVIATION_MIN = 0.1f;
	public static final float DEVIATION_DEFAULT = 1.4F;
	
	public static final int SIZE_MAX = 12;
	public static final int SIZE_MIN = 1;
	public static final int SIZE_DEFAULT = 5;
	
	static final float A = (float)Math.sqrt(2*Math.PI); 

	//properties:
	public static final String DEVIATION_PROP_ID = "deviation",
		SIZE_PROP_ID = "size";
	static {
		BlurTransformation.props= new Property[] {
				new NumericProperty(DEVIATION_PROP_ID, DEVIATION_DEFAULT, DEVIATION_MAX, DEVIATION_MIN), 
			new NumericProperty(SIZE_PROP_ID, SIZE_DEFAULT, SIZE_MAX, SIZE_MIN)
		};
	};
	
	//<attribs
	/** standar deviation */
	float deviation=DEVIATION_DEFAULT;	
	int size=SIZE_DEFAULT;
	
	private Kernel kernelx, kernely;
	private Kernel kernel;	
	
	public GaussianSmoothTransformation(float factor) {
		this.deviation = factor;
	}
	
	public GaussianSmoothTransformation() {
		this(DEVIATION_DEFAULT);
	}
	
	/**
	 * @param d standar deviation
	 * @return gaussian function with mean==0 evaluated in x
	 */
	float gaussian(float x, float d) {
		return ((float)1/(d*A)) * ((float)Math.exp(x*x/(2*d*d))); 
	}
	
	 /**
	  * @param d standar deviation
	  */
	float gaussian(float x, float y, float d) {
		return ((float)((float)1/(2*Math.PI*d*d)) * ((float)Math.exp((-1)*((x*x+y*y)/(2*d*d)))));
	}
	
	
	Kernel getKernelX() {
		return null;
	}
	
	Kernel getKernelY() {
		return null;
	}
	
	Kernel getKernel() {
		float [] data = new float [size*size];
		int b = size/2;
		for(int i = 0; i<size; i++)
			for(int j=0; j<size; j++)
				data[i*size+j] = gaussian(i-b, j-b, deviation);
		return new Kernel(size, size, data);
	}

	void doKernel() {
		kernel = getKernel();
	}
	public BufferedImage applyTransformation(BufferedImage src) {
		src=ImageFormat.makeByteIndexed(src);
//		kernelx = getKernelX();
//		kernely = getKernelY();
//		return Misc.applyOp(Misc.applyOp(src, new ConvolveOp(kernelx)), 
//				new ConvolveOp(kernely));
		return ImageUtils.applyOp(src, new ConvolveOp(kernel));
	}

	public void setDeviation(Object p) {
		setDeviation(castValueToFloat(p));
	}
	public void setDeviation(float f) {
		deviation=f;
		doKernel();
	}

	
	public String shortDescription() {
		return "TODO";
	}
	public void setSize(Object p) {
		setSize(castValueToInt(p));
	}
	public void setSize(int x) {
		size = x;
		doKernel();	
	}


}
