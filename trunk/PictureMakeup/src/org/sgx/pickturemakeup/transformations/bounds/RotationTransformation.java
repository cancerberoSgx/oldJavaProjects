package org.sgx.pickturemakeup.transformations.bounds;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import org.sgx.pickturemakeup.model.ImageTransformation;
import org.sgx.pickturemakeup.model.Property;


public class RotationTransformation extends ImageTransformation {
	
	public static float THETA_MAX = ((float)(2*Math.PI));	
	public static float THETA_MIN = 0f;
	public static float THETA_DEFAULT = 0.1f;
	
	public static float X_MAX = 1f;
	public static float X_MIN = 0f;
	public static float X_DEFAULT = 0.5f;
	
	public static float Y_MAX = 1;
	public static float Y_MIN = 0f;
	public static float Y_DEFAULT = 0.5f;
	
	/** centro y ángulos de rotación. 
	 * Nota el centro est� expresado en coords screen con resp a pantalla y es un porcentaje */
	float x_percent=X_DEFAULT, y_percent=Y_DEFAULT, theta = THETA_DEFAULT;	
	
	public RotationTransformation() {
		this(X_DEFAULT, Y_DEFAULT, THETA_DEFAULT);
	}
	/**
	 * 
	 * @param x coord horizontal del centro de rotación
	 * @param y coord verical del centro de rotación
	 * @param theta theta ángulo de rotación en radianes. a valores positivos 
	 * rotaciones en sentido antihorario 
	 */
	public RotationTransformation(float x, float y, float theta) {
		this.x_percent = x;
		this.y_percent = y;
		this.theta = theta;
	}
	
	/**
	 * rotación con centro de rotación en el centro de la imágne 
	 * @param theta ángulo de rotación en radianes. a valores positivos 
	 * rotaciones en sentido antihorario 
	 */
	public RotationTransformation(float theta) {	
		this(X_DEFAULT, Y_DEFAULT, theta);
	}

	public BufferedImage applyTransformation(BufferedImage src) {	
		AffineTransform tx = new AffineTransform();
		double x=src.getWidth()*x_percent,y=src.getHeight()*y_percent;		
	    tx.rotate(theta, x, y);
	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
	    return op.filter(src, null);
	}
	public String shortDescription() {
		return "TODO";
	}

	public void setTheta(float f) {
		theta=f;
	}

	public void setY(float f) {
		y_percent=f;
	}

	public void setX(float f) {
		x_percent=f;
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
