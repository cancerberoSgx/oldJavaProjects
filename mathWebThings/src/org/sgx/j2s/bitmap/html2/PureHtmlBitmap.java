//package org.sgx.j2s.bitmap.html2;
//
//import org.sgx.j2s.base.Color;
//import org.sgx.j2s.bitmap.Bitmap;
//import org.sgx.j2s.bitmap.DomUtils;
//
//public class PureHtmlBitmap implements Bitmap {
//	private static final String Bitmap_Tag = "div", Bitmap_Class=PureHtmlBitmap.class.getName();
//	static int idCounter=0;
//	static String getNewId() {
//		idCounter++;
//		return "bitmap"+idCounter;
//	}
//	
//	int width, height;
//	Color bg=Color.BLUE, fg=Color.BLACK;
//	private String elemId=null;
//	private int x;
//	private int y;
//
//	BitmapPoblator poblator=new BitmapPoblator1();
//	
//	public PureHtmlBitmap(int x, int y, int width, int height, String elemId) {
//		super();
//		this.x=x;
//		this.y=y;
//		this.width = width;
//		this.height = height;
//		this.elemId=elemId;
//		init();
//	}
//	public PureHtmlBitmap(int x, int y, int width, int height) {
//		this(x,y, width, height, null);
//	}
//	private void init() {
//		Object elem =null;
//		if(elemId==null) {
//			elemId = getNewId();
//			elem = DomUtils.createElement(Bitmap_Tag, DomUtils.getBody());
//		}
//		else {
//			elem = DomUtils.getElementById(elemId);
//		}
//		//TODO: elem!=null
//		DomUtils.setStyleProp(elem, "background-color", bg.toCSS());
//		DomUtils.setStyleProp(elem, "position", "absolute");
//		DomUtils.setStyleProp(elem, "top", y+"px");
//		DomUtils.setStyleProp(elem, "left", x+"px");
//		DomUtils.setStyleProp(elem, "width", width+"px");
//		DomUtils.setStyleProp(elem, "height", height+"px");
//		
//		DomUtils.insertStyleRules(
//			".pixel{" +
//				"position:absolute;" +
//				"width:1px;" +
//				"height:1px;" +
//				"font-size:0px;" +
//			"}\n" +
//		"");
//		
//		DomUtils.setStyleProp(elem, "display", "none");
//		DomUtils.setStyleProp(elem, "visibility", "hidden");
//		poblator.poblate(elem, width, height);
//		DomUtils.setStyleProp(elem, "display", "block");
//		DomUtils.setStyleProp(elem, "visibility", "visible");		
//		
//	}
//	
//
//
//	/* (non-Javadoc)
//	 * @see org.sgx.j2s.mybitmap.Bitmap#paint(int, int, org.sgx.j2s.raphael.Color)
//	 */
//	public void paint(int x, int y, Color c) {
//		if(!(0<=x &&x<width &&0<=y&&y<height)){
////			System.out.println("outside: "+x+", "+y);
//			return;
//		}
//		String pid = poblator.getPixelId(x,y);
//		Object pel = DomUtils.getElementById(pid);
//		if(pel==null) {
//			System.out.println("ERROR: pixel "+x+","+y+" has not representing element");
//			return;
//		}
//		DomUtils.setStyleProp(pel, "background-color", c.toCSS());
//	}
//	public void erase(Color white) {
//		throw new RuntimeException("not implemented");
//	}
//	public void line(int x1, int y1, int x2, int y2, Color c) {
//		throw new RuntimeException("not implemented");
//	}
//	public void flush(Color white) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
