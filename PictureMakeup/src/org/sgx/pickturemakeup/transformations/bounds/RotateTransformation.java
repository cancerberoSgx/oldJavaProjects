package org.sgx.pickturemakeup.transformations.bounds;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import org.sgx.pickturemakeup.model.ImageTransformation;
import org.sgx.pickturemakeup.model.Property;


public class RotateTransformation extends ImageTransformation {
	/** centro y ángulos de rotación */
	double x, y, theta;	
	boolean defaultCenter;

	/**
	 * 
	 * @param x coord horizontal del centro de rotación
	 * @param y coord verical del centro de rotación
	 * @param theta theta ángulo de rotación en radianes. a valores positivos 
	 * rotaciones en sentido antihorario 
	 */
	public RotateTransformation(double x, double y, double theta) {
		this.x = x;
		this.y = y;
		this.theta = theta;
		defaultCenter=false;
	}
	
	/**
	 * rotación con centro de rotación en el centro de la imágne 
	 * @param theta ángulo de rotación en radianes. a valores positivos 
	 * rotaciones en sentido antihorario 
	 */
	public RotateTransformation(double theta) {	
		this.theta = theta;
		defaultCenter=true;
	}

	public BufferedImage applyTransformation(BufferedImage src) {	
		AffineTransform tx = new AffineTransform();
		if(defaultCenter) {
			x=src.getWidth()/2;
			y=src.getHeight()/2;
		}
	    tx.rotate(theta, x, y);
	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
	    return op.filter(src, null);
	}
	public String shortDescription() {
		return "TODO";
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
