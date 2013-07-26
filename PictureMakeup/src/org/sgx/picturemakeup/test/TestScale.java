package org.sgx.picturemakeup.test;


import java.awt.image.BufferedImage;
import java.io.IOException;

import org.sgx.picturemakeup.gui.ImageWidgetImpl;
import org.sgx.picturemakeup.transformations.bounds.ScaleTransformation;
import org.sgx.utils.ImageUtils;



public class TestScale {

	public static void main(String [] a) {
		try {
			BufferedImage src = ImageWidgetImpl.getDefaultImg(); //ImageUtils.loadFromFile(ImageWidgetImpl.getDefaultImg());			
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
