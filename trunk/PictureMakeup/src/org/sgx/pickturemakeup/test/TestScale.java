package org.sgx.pickturemakeup.test;


import java.awt.image.BufferedImage;
import java.io.IOException;

import org.sgx.pickturemakeup.gui.ImageWidgetImpl;
import org.sgx.pickturemakeup.transformations.bounds.ScaleTransformation;
import org.sgx.utils.ImageUtils;



public class TestScale {

	public static void main(String [] a) {
		try {
			BufferedImage src = ImageUtils.loadFromFile(ImageWidgetImpl.defaultImgPath);			
			ScaleTransformation t = new ScaleTransformation(src);
			t.setNewDimensions(32,32);
			BufferedImage dest = t.applyTransformation(src);
			ImageUtils.saveToFile(dest, "/home/sgx/pppp.png", "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
