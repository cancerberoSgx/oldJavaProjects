package org.sgx.pickturemakeup.transformations.colors;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import org.sgx.pickturemakeup.model.ImageTransformation;
import org.sgx.pickturemakeup.model.NumericProperty;
import org.sgx.pickturemakeup.model.Property;
import org.sgx.pickturemakeup.transformations.BlurTransformation;
import org.sgx.utils.ImageUtils;




public class ToGrayTransformation extends ImageTransformation {

	//	properties:
	static {
		ToGrayTransformation.props= new Property[] {};
	};
	
	public BufferedImage applyTransformation(BufferedImage src) {
	    ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	    return ImageUtils.applyOp(src,new ColorConvertOp(cs, null));
	}
	public String shortDescription() {
		return "transform to gray scale";
	}
	
}


