package org.sgx.picturemakeup.persistence;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PNGLoader implements Loader {

	public String getFormatDescription() {
		return "png";
	}

	public BufferedImage load(String path)  throws IOException{
		return ImageIO.read(new File(path)); 
	}

	public boolean understand(String ext) {
		System.out.println("understand "+ext);
		if(ext.equals("png"))
			return true;
		else
			return false;
	}

}
