package org.sgx.pickturemakeup.transformations;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import org.sgx.pickturemakeup.model.ImageTransformation;
import org.sgx.pickturemakeup.model.NumericProperty;
import org.sgx.pickturemakeup.model.Property;
import org.sgx.pickturemakeup.utils.ImageFormat;
import org.sgx.utils.ImageUtils;
import org.sgx.utils.Utils;




/**
 * edge detecting simple algorithm
 * 
 * @author sgx
 *
 */
public class RobertCrossTransformation extends ImageTransformation {

	public static double FACTOR_MIN = 0.1;
	public static double FACTOR_MAX = 5.0;
	public static double FACTOR_DEFAULT = 1.0;
	public static int SIZE_MAX = 10;
	public static int SIZE_MIN = 1;
	public static int SIZE_DEFAULT = 2;
	
//	properties:
	public static final String FACTOR_PROP_ID = "factor",
		SIZE_PROP_ID = "size";
	static {
		BlurTransformation.props= new Property[] {
				new NumericProperty(FACTOR_PROP_ID, FACTOR_DEFAULT, FACTOR_MAX, FACTOR_MIN), 
			new NumericProperty(SIZE_PROP_ID, SIZE_DEFAULT, SIZE_MAX, SIZE_MIN)
		};
	};
	
	float factor=1.0f;
	/**
	 * debería ser un número de 1 a 10
	 */
	int size;
	
	public RobertCrossTransformation(float factor, int size) {
		this.factor = factor;
		this.size=size;
	}
	public RobertCrossTransformation() {
		this(1.0f,2);
	}

	Kernel makeKernel(boolean TL2BR) {
		return new Kernel(size+1, size+1, makeMatrix(TL2BR));
	}
	/**
	 * @param TL2BR si se trata del kernel para la detección de bordes respectivo a
	 * la diagonal que va desde arriba-derecha a abajo-izquierda
	 * @return la matriz para construir el kernel apropiado para la robercross del size y factor actuales
	 */	
	float[] makeMatrix(boolean TL2BR) {
		int n = size+1;
		float [][] m = new float[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i==0 && j==0 && TL2BR)
					m[i][j]=factor;
				else if(i==0 && j==n-1 && !TL2BR)
					m[i][j]=factor;
				else if(i==n-1 && j==0 && !TL2BR) 
					m[i][j]=-factor;
				else if(i==n-1 && j==n-1 && TL2BR) 
					m[i][j]=-factor;
				else
					m[i][j]=0;
			}		
		}
		float []r = new float[n*n];
		int k=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				r[k]=m[i][j];
				k++;
			}
		}
		
		System.out.println("ROBERT: "+Utils.toStringF(r));
		return r;
	}
	
	
	public BufferedImage applyTransformation(BufferedImage src) {
		src=ImageFormat.makeByteIndexed(src);		
		src=ImageUtils.applyOp(src, new ConvolveOp(makeKernel(true)));		
		return ImageUtils.applyOp(src, new ConvolveOp(makeKernel(false)));
	}
	public float getFactor() {
		return factor;
	}
	public void setFactor(float factor) {
		this.factor = factor;
	}
	public int getSize() {
		return size;
	}
	public void setFactor(Object value) {
		setFactor(castValueToFloat(value));
	}
	public void setSize(Object value) {
		setSize(castValueToInt(value));
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public String shortDescription() {
		return "TODO";
	}

}


