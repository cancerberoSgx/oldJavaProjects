package org.sgx.pickturemakeup.transformations.morphologic;

import java.awt.Point;
import java.awt.image.BufferedImage;

import org.sgx.pickturemakeup.model.ImageTransformation;

public  abstract class MorphologicTransformation extends ImageTransformation {
	float [] [] structElem;
	Point structElemCenter;

	@Override
	public BufferedImage applyTransformation(final BufferedImage src) {
		int W = src.getWidth(), H = src.getHeight();
		BufferedImage dest = new BufferedImage(W,H,BufferedImage.TYPE_INT_ARGB);
		for(int i=0; i<W;i++) {
			for(int j = 0; j<H;j++) {
				applyStructElem(src,dest,i,j);
			}
		}
		return dest;
	}

	public abstract void applyStructElem(final BufferedImage img, 
			BufferedImage dest, int x, int y);
	
	@Override
	public abstract String shortDescription();

	public float[][] getStructElem() {
		return structElem;
	}

	public void setStructElem(float[][] structElem) {
		this.structElem = structElem;
	}

	public Point getStructElemCenter() {
		return structElemCenter;
	}

	public void setStructElemCenter(Point structElemCenter) {
		this.structElemCenter = structElemCenter;
	}
	
}
