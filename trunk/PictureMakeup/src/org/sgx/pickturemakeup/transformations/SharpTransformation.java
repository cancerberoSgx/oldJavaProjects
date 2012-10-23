package org.sgx.pickturemakeup.transformations;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import org.sgx.pickturemakeup.model.ImageTransformation;
import org.sgx.pickturemakeup.model.NumericProperty;
import org.sgx.pickturemakeup.model.Property;
import org.sgx.pickturemakeup.utils.ImageFormat;
import org.sgx.utils.ImageUtils;




public class SharpTransformation extends ImageTransformation {

	public static final float WEIGHT_MAX = 5.0f;
	public static final float WEIGHT_MIN = 0.1f;
	public static final float WEIGHT_DEFAULT = 1.0F;
	
	//properties:
	public static final String WEIGHT_PROP_ID = "weight";
	static {
		SharpTransformation.props= new Property[] {
			new NumericProperty(WEIGHT_PROP_ID, WEIGHT_DEFAULT, WEIGHT_MAX, WEIGHT_MIN)
		};
	};
	
	//<attribs
	/** weight for the center point */
	float weight=WEIGHT_DEFAULT;
	Kernel kernel;
	
	void doKernel() {
		kernel = new Kernel(4, 4, 
				new float[] {
				-1/9f, -1/9f, -1/9f,-1/9f,
				-1/9f, weight, weight,-1/9f,
				-1/9f, weight, weight,-1/9f, 
				-1/9f, -1/9f, -1/9f,-1/9f				
		});
	}
	
	public BufferedImage applyTransformation(BufferedImage src) {
		src=ImageFormat.makeByteIndexed(src);
		return ImageUtils.applyOp(src, new ConvolveOp(kernel));
	}

	public String shortDescription() {
		return "Increases the contrast between bright and dark regions to bring out features";
	}

	public void setWeight(Object f) {
		setWeight(castValueToFloat(f));
	}
	public void setWeight(float f) {
		weight=f;
		doKernel();
	}



}
