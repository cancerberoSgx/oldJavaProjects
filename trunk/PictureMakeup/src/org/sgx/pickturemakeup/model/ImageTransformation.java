package org.sgx.pickturemakeup.model;

import java.awt.image.BufferedImage;


public abstract class ImageTransformation extends AbstractPropertyHaver{

	public abstract BufferedImage applyTransformation(BufferedImage src);
	
	public abstract String shortDescription();
	
}
