//package org.sgx.j2s.bitmap.raphael;
//
//import org.sgx.j2s.base.Color;
//import org.sgx.j2s.bitmap.Bitmap;
//import org.sgx.j2s.raphael.Paper;
//import org.sgx.j2s.raphael.Path;
//
//public class RaphaelBitmap implements Bitmap {
//	private Paper paper;
//	private Path path;
//	public RaphaelBitmap(int x, int y, int w, int h) {
//		paper = new Paper(x,y,w,h);
//		path=paper.path(new Object[][]{{"stroke", "black"}});
//	}
//	public void paint(int x, int y, Color c) {
//		path.moveTo(x-1, y);
//		path.lineTo(x, y);
//	}
//	
//	public static void main(String[]a) {
//		RaphaelBitmap b = new RaphaelBitmap(1,1,300,300);
//		b.paint(2,2,Color.BLACK);
//	}
//	public void erase(Color white) {
//		throw new RuntimeException("not implemented");
//	}
//	public void line(int x1, int y1, int x2, int y2, Color c) {
//		paper.path(new String[][]{{"stroke", c.toCSS()}}).moveTo(x1, y1).lineTo(x2, y2).andClose();
//	}
//	public void flush(Color white) {
//		// TODO Auto-generated method stub
//		
//	}
//	public Object getRepresentation() {
//		return null;
//	}
//
//}
