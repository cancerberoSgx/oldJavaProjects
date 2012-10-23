package org.sgx.pickturemakeup.persistence;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PNGSaver implements Saver {

	public String LooseDataMsg(BufferedImage buffi) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFormatDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(BufferedImage buffi, String path) throws IOException {
		ImageIO.write(buffi, "png", new File(path));		
	}

	public boolean understand(String ext) {
		if(ext.toLowerCase()=="png") 
			return true;
		else return false;
	}

}
