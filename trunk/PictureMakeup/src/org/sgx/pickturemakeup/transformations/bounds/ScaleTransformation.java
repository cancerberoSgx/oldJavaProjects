package org.sgx.pickturemakeup.transformations.bounds;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.sgx.pickturemakeup.model.ImageTransformation;
import org.sgx.pickturemakeup.model.Property;
import org.sgx.pickturemakeup.model.TransformationException;
import org.sgx.utils.ImageUtils;




/**
 * transformación para redimensionar una imágen
 * @author sgx
 *
 */
public class ScaleTransformation extends ImageTransformation {
	
	
	double widthProp, heightProp;
	int width, height;
	
	public ScaleTransformation(BufferedImage buffi) {
		this.width=buffi.getWidth();
		this.height=buffi.getHeight();
	}	
	
	static final double margin=1.0;
	static int matrixSize=3;
	
	public BufferedImage applyTransformation(BufferedImage src)  {
		AffineTransform tx = new AffineTransform();
		tx.scale(widthProp, heightProp);	    
		
		//working matrix?
		boolean works=false;
		int vsize=matrixSize*matrixSize;
		double [] M = new double [vsize];
		System.out.println(M.length);
		tx.getMatrix(M);
		double cont=0.0 ;
		for(int i=0;i<vsize;i++) {
			cont+=M[i];
		}
		works = cont>margin;
		if(works) {			
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			return op.filter(src, null);
		}
		else {
			return src;
//			throw new TransformationException("dimensiones no soportadas");
		}
	}
	public String shortDescription() {
		return "TODO";
	}

public void setProportions(double newW, double newH) {
	widthProp=newW;
	heightProp=newH;		
}

public void setNewDimensions(int newWidth, int newHeight) {
	widthProp=((double)newWidth)/((double)width);
	heightProp=((double)newHeight)/((double)height);
}

public static void scaleSize(String srcPath, String destPath, String format, 
		int newWidth, int newHeight) throws IOException, TransformationException {
	BufferedImage src = ImageUtils.loadFromFile(srcPath);			
	ScaleTransformation t = new ScaleTransformation(src);
	t.setNewDimensions(newWidth,newHeight);
	BufferedImage dest = t.applyTransformation(src);
	ImageUtils.saveToFile(dest, destPath, format);
}

	public static void scaleProportions(String srcPath, String destPath, String format, 
			double widthProportion, double heightProportion) throws IOException, TransformationException {
		BufferedImage src = ImageUtils.loadFromFile(srcPath);			
		ScaleTransformation t = new ScaleTransformation(src);
		t.setProportions(widthProportion, heightProportion);
		BufferedImage dest = t.applyTransformation(src);
		ImageUtils.saveToFile(dest, destPath, format);
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
