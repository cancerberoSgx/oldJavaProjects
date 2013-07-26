package org.sgx.picturemakeup.test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.sgx.utils.ImageUtils;



public class TiffTest {
	public static void main(String [] a) throws IOException {
		String defaultImgPath = "/home/sgx/workspace5/PictureMakeup/gui/images/846.jpg", 
			outpath = "/home/sgx/workspace5/PictureMakeup/gui/images/846_out.png";
		BufferedImage imgrsc = ImageIO.read(new File(defaultImgPath)), 
			img =make_ARGB(imgrsc);
		for(int i=0;i<img.getWidth(); i++) {
			for(int j = 0; j<img.getHeight()/2; j++) {
				int rgb=img.getRGB(i,j);
				Color c = buildColor(rgb);
				Color out = new Color(c.getRed(), c.getGreen(), c.getBlue(), 250);
				img.setRGB(i, j, buildColor(out));
			}
			for(int j = img.getHeight()/2; j<img.getHeight(); j++) {
				int rgb=img.getRGB(i,j);
				Color c = buildColor(rgb);
				Color out = new Color(c.getRed(), c.getGreen(), c.getBlue(), 10);
				img.setRGB(i, j, buildColor(out));
			}
		}
		 ImageIO.write(img, "png", new File(outpath));
	}
	public static BufferedImage make_ARGB(BufferedImage buffi) {
			if(buffi.getType()!=BufferedImage.TYPE_INT_ARGB) 
				return ImageUtils.cloneImage(buffi, BufferedImage.TYPE_INT_ARGB);
			else return buffi;
	};
	public static Color buildColor(int argb) {
		int alpha = (argb >> 24) & 0xff;
		int red = (argb >> 16) & 0xff;
		int green = (argb >> 8) & 0xff;
		int blue = argb & 0xff;
		return new Color(red,green,blue,alpha);
	}
	public static int buildColor(Color c) {
		int a = (c.getAlpha()<<24) & 0xff000000;
		int r = (c.getRed()<<16) & 0x00ff0000;
		int g = (c.getGreen()<<8) & 0x0000ff00;
		int b = (c.getBlue()) & 0x000000ff;
		return a|r|g|b;
	}
}
