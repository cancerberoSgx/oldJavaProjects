package org.sgx.picturemakeup.utils;

import java.awt.image.BufferedImage;

import org.sgx.utils.ImageUtils;


/**
 * imformación sobre formatos soportados, primitivas de manipulacion de imagenes, etc
 * @author sgx
 *
 */
public class ImageFormat {	
	
	public static BufferedImage makeByteIndexed(BufferedImage buffi) {
		if(buffi.getType()!=BufferedImage.TYPE_BYTE_INDEXED) 
			return ImageUtils.cloneImage(buffi, BufferedImage.TYPE_BYTE_INDEXED);
		else return buffi;
	}
	
	public static BufferedImage makeRGB(BufferedImage buffi) {
		if(buffi.getType()!=BufferedImage.TYPE_INT_RGB) 
			return ImageUtils.cloneImage(buffi, BufferedImage.TYPE_INT_RGB);
		else return buffi;
	}
	

}
