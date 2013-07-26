package org.sgx.picturemakeup.transformations;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import org.sgx.picturemakeup.model.ImageTransformation;
import org.sgx.picturemakeup.model.Matrix;
import org.sgx.picturemakeup.model.MatrixProperty;
import org.sgx.picturemakeup.model.NumericProperty;
import org.sgx.picturemakeup.model.Property;
import org.sgx.picturemakeup.utils.ImageFormat;
import org.sgx.utils.ImageUtils;




public class ConvolutionTransformation extends ImageTransformation{

	//properties:
	public static final String KERNEL_PROP_ID = "kernel";
	
	static {
		props= new Property[] {
				new MatrixProperty(KERNEL_PROP_ID, "kernel of convolution", 
						MatrixProperty.FLOAT_TYPE, 3, 3)
		};
	};
	
	//attribs
	Matrix kernel=Matrix.make(3,3,0);	
	
	public void setKernel(Object k) {
		if(k instanceof Matrix) {
			System.err.println("is a matrix!!: "+k.getClass());
			try {				
				kernel=(Matrix)k;
			} catch(Exception e) {
				e.printStackTrace();				
			}
		}
		else {
			System.err.println("not a matrix: "+k.getClass());
		}
	}
	
	@Override
	public BufferedImage applyTransformation(BufferedImage src) {		
		src=ImageFormat.makeByteIndexed(src);
		Kernel k = doKernel();
		System.out.println("convolve: Matrix: "+kernel+"   ---  kernel: "+kernelToString(k));
		return ImageUtils.applyOp(src, new ConvolveOp(k));
	}

	@Override
	public String shortDescription() {
		return "general convolution transformation";
	}
	
	Kernel doKernel() {
		float [] k = new float [kernel.getN()*kernel.getM()];
		for(int i = 0; i<kernel.getN(); i++)
			for(int j=0; j<kernel.getM(); j++) 
				k[i*kernel.getN()+j]=kernel.get(i, j);		
		return new Kernel(kernel.getN(),kernel.getM(),k);
	}
	
	String kernelToString(Kernel k) {
		String s="[";
		float[] d  =k.getKernelData(null);		
		for(int i = 0; i<d.length; i++)
			s=s+d[i]+",";
		return s+"]";
	}
		
}
