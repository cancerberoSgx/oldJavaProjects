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
 * laplacian of gaussian : http://homepages.inf.ed.ac.uk/rbf/HIPR2/log.htm
 * 
 * puede decirse que es un high pass filter laplaciano pero con un suavisado gaussiano
 * 
 * @author sgx
 *
 */
public class LoGTransformation extends ImageTransformation {

	public static final int SIZE_MAX = 20;
	public static final int SIZE_MIN = 2;
	public static final int SIZE_DEFAULT = 7;
	
	public static final float DEVIATION_DEFAULT = 0.3F;
	public static final float DEVIATION_MAX = 1.0F;
	public static final float DEVIATION_MIN = 0.1F;
	
	//	properties:
	public static final String DEVIATION_PROP_ID = "deviation",
		SIZE_PROP_ID = "size";
	static {
		BlurTransformation.props= new Property[] {
				new NumericProperty(DEVIATION_PROP_ID, DEVIATION_DEFAULT, DEVIATION_MAX, DEVIATION_MIN), 
			new NumericProperty(SIZE_PROP_ID, SIZE_DEFAULT, SIZE_MAX, SIZE_MIN)
		};
	};
	
	/** weight for the center point */
	int size=SIZE_DEFAULT;
	/** standar deviation */
	float d = DEVIATION_DEFAULT;
	
	Kernel kernel;
	
	void doKernel() {
		kernel = getLaplacianKernel(size, d);
//		kernel = getOneLoGKernel();
//		printKernel(kernel);
	}
	
	public BufferedImage applyTransformation(BufferedImage src) {
		src=ImageFormat.makeByteIndexed(src);
		return ImageUtils.applyOp(src, new ConvolveOp(kernel));
	}

	public String shortDescription() {
		return "see http://homepages.inf.ed.ac.uk/rbf/HIPR2/log.htm";
	}
	public void setSize(Object p) {
		setSize(castValueToInt(p));
	}
	public void setSize(int s) {
		size=s;
		doKernel();
	}
	public void setDeviation(Object p) {
		setDeviation(castValueToFloat(p));
	}
	public void setDeviation(float d) {
		this.d=d*(-1)+1;
		doKernel();
	}

	/**
	 * Laplacian of Gaussian
	 * @param x
	 * @param y
	 * @param d standar deviation
	 * @return
	 */
	static float getLaplacianOf(int x, int y, float d) {
		return (1f)/(((float)Math.PI)*d*d*d*d) * (1f-(x*x+y*y)/(2*d*d)) * (((float)Math.exp((x*x+y*y)/(-2f*d*d))));
	}
	
	static public Kernel getLaplacianKernel(int n, float d) {
		float [] A = new float[n*n];
		int r = n/2;
		for(int i = 0; i< n; i++)
			for(int j = 0; j<n; j++)
				A[i*n+j] = getLaplacianOf(i-r,j-r,d);
		return new Kernel(n,n,A);
	}
	
	static public Kernel getOneLoGKernel() {
		return new Kernel(3,3, new float[] {
			0.12601699f, 1.1609108f, 0.12601699f, 
			1.1609108f, -12.433979f, 1.1609108f, 
			0.12601699f, 1.1609108f, 0.12601699f
		});
	}
	
//	public void printKernel(Kernel k) {
//		int n =k.getWidth();
//		float []f = new float[n*n];
//		k.getKernelData(f);
//		for(int i = 0; i< n; i++) {
//			for(int j = 0; j<n; j++)
//				System.out.print(f[i*n+j]+", ");
//			System.out.println("");
//		}
//		System.out.println("");
//	}
	public static void main(String [] a) {
		int n = 3;
		float d = 0.4f;
		for(int i = 0; i< n; i++) {
			for(int j = 0; j<n; j++)
				System.out.print(getLaplacianOf(i-1,j-1,d)+", ");
			System.out.println("");
		}
	}

}
