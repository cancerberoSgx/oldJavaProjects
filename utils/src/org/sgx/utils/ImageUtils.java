package org.sgx.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * interfaz con las operaciones de bajo nivel de manipulación de imágenes de awt
 * 
 * @author sgx
 *
 */
public class ImageUtils {
	
	public static BufferedImage applyOp(BufferedImage buffi, BufferedImageOp op) {
		return op.filter(buffi, null);
	}
	
	public static BufferedImage cloneImage(BufferedImage buffi) {
		return cloneImage(buffi, buffi.getType());
	}
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
	
	/**
	 * @param image
	 * @param type one of BufferedImage.TYPE_INT_*
	 * @return una imagen identica a image pero con el tipo especificado
	 */
	public static BufferedImage cloneImage(BufferedImage image, int type) {	    
	    BufferedImage bimage = new BufferedImage(image.getWidth(null), 
	    		image.getHeight(null), type);
	    Graphics2D g = bimage.createGraphics();
	    g.drawImage(image, 0, 0, null);
	    g.dispose();
	    return bimage;
	}
	
	public static BufferedImage loadFromFile(String imagePath) throws IOException {
		return ImageIO.read(new File(imagePath));
	}
	
	public static void saveToFile(BufferedImage buffi, String imagePath, String formatName) throws IOException {
		ImageIO.write(buffi, formatName, new File(imagePath));
	}

}

