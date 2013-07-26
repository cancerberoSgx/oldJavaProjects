package org.sgx.picturemakeup.transformations;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import org.sgx.picturemakeup.model.ImageTransformation;
import org.sgx.picturemakeup.model.Property;


public class ShearTransformation extends ImageTransformation {
	
	public static final double SHEARX_MAX = 1.0;
	public static final double SHEARX_MIN = 0.0;
	public static final double SHEARX_DEFAULT = 0.001;
	
	public static final double SHEARY_MAX= 1.0;
	public static final double SHEARY_MIN= 0.0;
	public static final double SHEARY_DEFAULT= 0.001;
	
	 
	double shearx=SHEARX_DEFAULT, sheary=SHEARY_DEFAULT;

	public ShearTransformation(double shearx, double sheary) {
		this.shearx = shearx;
		this.sheary = sheary;
	}
	
	public ShearTransformation() {
		this(SHEARX_DEFAULT, SHEARY_DEFAULT);
	}

	public BufferedImage applyTransformation(BufferedImage src) {	
		AffineTransform tx = new AffineTransform();		
	    tx.shear(shearx, sheary);
	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
	    return op.filter(src, null);
	}
	public String shortDescription() {
		return "TODO";
	}

	public void setShearX(float f) {
		this.shearx=f;
	}

	public void setShearY(float f) {
		this.sheary=f;
	}

	public void changePropertyValue(String propId, Object value) {
		// TODO Auto-generated method stub
		
	}

	public Object getPropertyValue(String propertyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Property[] getProperties() {
		// TODO Auto-generated method stub
		return null;
	}
}
