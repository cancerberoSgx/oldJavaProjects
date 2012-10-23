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
 * edge detecting simple algorithm
 * 
 * @author sgx
 *
 */
public class SobelTransformation extends ImageTransformation {
	public static int GROSOR_MAX = 5;
	public static int GROSOR_MIN = 1;
	public static int GROSOR_DEFAULT = 2;
	
	public static float MULTIPLER_MAX = 5.0f;
	public static float MULTIPLER_MIN = 0.0f;
	public static float MULTIPLER_DEFAULT = 0.5f;
	
	//properties:
	public static final String GROSOR_PROP_ID = "grosor",
		MULTIPLER_PROP_ID ="multiplier";
	static {
		BlurTransformation.props= new Property[] {
				new NumericProperty(GROSOR_PROP_ID, GROSOR_DEFAULT, GROSOR_MAX, GROSOR_MIN), 
			new NumericProperty(MULTIPLER_PROP_ID, MULTIPLER_DEFAULT, MULTIPLER_MAX, MULTIPLER_MIN)
		};
	};
	
	//<attribs
	float multiplier = MULTIPLER_DEFAULT; 	
	int grosor;
	
	public SobelTransformation(int grosor) {
		this.grosor = grosor;
	}
	public SobelTransformation() {
		this(GROSOR_DEFAULT);
	}

	public BufferedImage applyTransformation(BufferedImage src) {
		src=ImageFormat.makeByteIndexed(src);
		Kernel kernel;
		
		if(grosor==1) {			
			kernel = new Kernel(3, 3,
				multiplyFor(
					 new float[] {
					-1, 0, +1,
					-2, 0, +2,
					-1, 0, 1}, 
					3, multiplier)
			);		
			src=ImageUtils.applyOp(src, new ConvolveOp(kernel));
			kernel = new Kernel(3, 3,
				multiplyFor(
					new float[] {
					1, 2, 1,
					0, 0, 0,
					-1,-2,-1}, 
					3, 
					multiplier)
			);
		}
		else if(grosor==2) {
			kernel = new Kernel(4, 4,
					multiplyFor(
					new float[] {
					-1, 0, 0, 1,
					-2, 0, 0, 2,
					-2, 0, 0, 2,
					-1, 0, 0, 1},
					4, multiplier));		
			src=ImageUtils.applyOp(src, new ConvolveOp(kernel));
			kernel = new Kernel(4, 4,
					multiplyFor(
					new float[] {
					+1, +2, +2, +1,
					0, 0, 0, 0,
					0, 0, 0, 0,
					0, 0, 0, 0,
					-1, -2, -2, -1},
					4, multiplier));
		}
		else if(grosor==3) {
			kernel = new Kernel(5, 5,
					multiplyFor(new float[] {
				-1, 0, 0, 0, 1,
				-2, 0, 0, 0, 2,
				-3, 0, 0, 0, 3,
				-2, 0, 0, 0, 2,
				-1, 0, 0, 0, 1},
				5, multiplier));		
			src=ImageUtils.applyOp(src, new ConvolveOp(kernel));
			kernel = new Kernel(5, 5,
					multiplyFor(new float[] {
				+1, +2, +3, +2, +1,
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,
				-1, -2, -3, -2, -1},
				5, multiplier));	
		}
		else if(grosor==4) {
			kernel = new Kernel(6, 6,
					multiplyFor(new float[] {
				-1, 0, 0, 0, 0, 1,
				-2, 0, 0, 0, 0, 2,
				-3, 0, 0, 0, 0, 3,
				-3, 0, 0, 0, 0, 3,
				-2, 0, 0, 0, 0, 2,
				-1, 0, 0, 0, 0, 1},
				6, multiplier));		
			src=ImageUtils.applyOp(src, new ConvolveOp(kernel));
			kernel = new Kernel(6, 6,
					multiplyFor(new float[] {
				+1, +2, +3, +3, +2, +1,
				0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 
				0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0,
				-1, -2, -3, -3, -2, -1},
				6, multiplier));	
		}
		else if(grosor==5) {
			kernel = new Kernel(7, 7,
					multiplyFor(new float[] {
				-1, 0, 0, 0, 0, 0, 1,
				-2, 0, 0, 0, 0, 0, 2,
				-3, 0, 0, 0, 0, 0, 3,
				-4, 0, 0, 0, 0, 0, 4,
				-3, 0, 0, 0, 0, 0, 3,
				-2, 0, 0, 0, 0, 0, 2,
				-1, 0, 0, 0, 0, 0, 1},
				7, multiplier));		
			src=ImageUtils.applyOp(src, new ConvolveOp(kernel));
			kernel = new Kernel(7, 7,
					multiplyFor(new float[] {
				+1, +2, +3, +4, +3, +2, +1,
				0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 
				-1, -2, -3, -4, -3, -2, -1},
				7, multiplier));	
		}
		else throw new RuntimeException("bad grosor: "+grosor);
		
		return ImageUtils.applyOp(src, new ConvolveOp(kernel));
	}
	public int getGrosor() {
		return grosor;
	}
	
	float [] multiplyFor(float [] data, int n, float x) {
		float [] D = new float[n*n];
		for(int i = 0; i< n; i++)
			for(int j = 0; j<n; j++)
				D[i*n+j] = data[i*n+j]*x;
		return D;
	}
	
	public void setGrosor(Object grosor) {
		setGrosor(castValueToInt(grosor));
	}
	public void setGrosor(int grosor) {
		this.grosor = grosor;
	}
	public String shortDescription() {
		return "TODO";
	}
	public void setMultiplier(Object f) {
		setMultiplier(castValueToFloat(f));
	}
	public void setMultiplier(float f) {
		multiplier = f;
	}

}


