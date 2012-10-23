package org.sgx.j2s.bitmap.html2;

import org.sgx.j2s.bitmap.DomUtils;
import org.sgx.j2s.bitmap.JsStringBuffer;

public class BitmapPoblator1 implements BitmapPoblator {

//	/* (non-Javadoc)
//	 * @see org.sgx.j2s.bitmap.html.BitmapPoblator#poblate(java.lang.Object, int, int)
//	 */
//	public  void poblate(Object elem, int width, int height) {
//		String s = "";
//		String ssss="";
//		
////		int count=0;
////		int M=100;
//		/** var tmpArray = []; 
//		 * @j2sNative
//		 */{}
//		
//		for (int i = 0; i < width; i++) {
//			for (int j = 0; j < height; j++) {
//				s=getPixelElement(i,j);
//				/** tmpArray.push(s);
//				 * @j2sNative
//				 */{}
////				count++;
////				if(count>M) {
////					DomUtils.setInnerHtml(elem, s);
////					count=0;
////					s="";
////				}
//			}
//		}
//		/** s=tmpArray.join();
//		 * ssss=tmpArray.length;
//		 * @j2sNative
//		 */{}
//		 System.out.println("arrlength: "+ssss+" length: "+s.length());
//		 DomUtils.setInnerHtml(elem, s);
//	}
//	
	
	
	/* (non-Javadoc)
	 * @see org.sgx.j2s.bitmap.html.BitmapPoblator#poblate(java.lang.Object, int, int)
	 */
	public  void poblate(Object elem, int width, int height) {
		long time = System.currentTimeMillis();
		
		JsStringBuffer s = new JsStringBuffer();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				s.append(getPixelElement(i,j));				
			}
		}
		String ss = s.toString();
		System.out.println("time building string: "+(System.currentTimeMillis()-time)+"ms");
		time = System.currentTimeMillis();
		DomUtils.innerHtmlFast(elem, ss);
//		/**
//		 * @j2sNative
//		 * elem.innerHTML=ss;
//		 */{}
//		DomUtils.setInnerHtml(elem, ss);
		System.out.println("time setInnerHtml: "+(System.currentTimeMillis()-time)+"ms");
	}



	/* (non-Javadoc)
	 * @see org.sgx.j2s.bitmap.html.BitmapPoblator#getPixelId(int, int)
	 */
	public  String getPixelId(int x, int y) {
		return "pixel_"+x+"_"+y;
	}
	String getPixelElement(int x, int y) {
		return "<b id=\""+getPixelId(x,y)+"\" class=\"pixel\" " +
				"style=\"top:"+y+"px;left:"+x+"px\"></b>"; 
	}

}
