package org.sgx.pickturemakeup.transformations.pixel;

import java.awt.Color;
import java.awt.Image;

public class DitherInverse extends DitherTransformation {

	@Override
	public Color getDitherOposite(Pixel p, Image img) {
		return new Color(255-p.c.getRed(), 255-p.c.getGreen(), 
				255-p.c.getBlue(), 255-p.c.getAlpha());
	}

	@Override
	public String shortDescription() {
		return "";
	}

}
