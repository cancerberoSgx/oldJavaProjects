package org.sgx.j2s.bitmap;

import org.sgx.j2s.bitmap.canvas.CanvasBitmap;
import org.sgx.j2s.bitmap.html.MyBitmap;
//import org.sgx.j2s.bitmap.html2.PureHtmlBitmap;
//import org.sgx.j2s.bitmap.raphael.RaphaelBitmap;

public class BitmapFactory {
	/** 1 - IE, 2 FF, 3 opera 
	 * @j2sNative
	 * if (/MSIE/.test(navigator.userAgent) && !window.opera) {
	 * 	return 1; //IE
	 * }
	 * else if(window.opera)
	 * 	return 3;
	 * else
	 * 	return 2;
	 */
	public static int guessBrowser(){return 0;}
	

	public static Bitmap getDefault(int x, int y, int w,int h) {
//		return new PureHtmlBitmap(x,y,w,h);
//		return new RaphaelBitmap(x,y,w,h);
//		return new CanvasBitmap(x,y,w,h);
		if(guessBrowser()==1)
			return new MyBitmap(x,y,w,h);
		else
			return new CanvasBitmap(x,y,w,h);
	}

}
