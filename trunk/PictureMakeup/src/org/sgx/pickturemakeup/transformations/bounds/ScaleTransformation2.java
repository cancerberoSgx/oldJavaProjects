package org.sgx.pickturemakeup.transformations.bounds;
//package transformations;
//
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
//import utils.Misc;
//
//public class ScaleTransformation2 implements ImageTransformation { 
//
//	private double widthProp;
//	private double width;
//	private double heightProp;
//	private double height;
//
//	public BufferedImage applyTransformation(BufferedImage src) throws TransformationException {
//		return src.getScaledInstance()
//	}
//
//	public String shortDescription(){
//	return "scale the image bigger or smaller";
//	}
////	public void setProportions(double newW, double newH) {
////		widthProp=newW;
////		heightProp=newH;		
////	}
////
////	public void setNewDimensions(int newWidth, int newHeight) {
////		
////		widthProp=((double)newWidth)/((double)width);
////		heightProp=((double)newHeight)/((double)height);
////	}
////
////	public static void scaleSize(String srcPath, String destPath, String format, 
////			int newWidth, int newHeight) throws IOException {
////		BufferedImage src = Misc.loadFromFile(srcPath);			
////		ScaleTransformation t = new ScaleTransformation(src);
////		t.setNewDimensions(newWidth,newHeight);
////		BufferedImage dest = t.applyTransformation(src);
////		Misc.saveToFile(dest, destPath, format);
////	}
////
////		public static void scaleProportions(String srcPath, String destPath, String format, 
////				double widthProportion, double heightProportion) throws IOException {
////			BufferedImage src = Misc.loadFromFile(srcPath);			
////			ScaleTransformation t = new ScaleTransformation(src);
////			t.setProportions(widthProportion, heightProportion);
////			BufferedImage dest = src.getScaledInstance()t.applyTransformation(src);
////			Misc.saveToFile(dest, destPath, format);
////		}
////		
//
//}
