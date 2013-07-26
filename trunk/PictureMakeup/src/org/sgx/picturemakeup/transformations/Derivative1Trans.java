package org.sgx.picturemakeup.transformations;
//package transformations;
//
//import java.awt.Color;
//import java.awt.image.BufferedImage;
//
//import org.jscience.mathematics.number.Float64;
//import org.jscience.mathematics.vector.Matrix;
//
//import test.TestImageTransformation;
//import transformations.pixel.LogarithmTrans;
//import utils.Misc;
//
//import model.ImageTransformation;
//
//public class Derivative1Trans extends ImageTransformation{
//
//	public static final int dir_row=1;
//	public static final int dir_column=2;
//	
//	int direction;
//	
//	public Derivative1Trans() {
//		this(dir_row);
//	}
//	public Derivative1Trans(int direction) {
//		super();
//		this.direction = direction;
//	}
//	int[][] getInts(BufferedImage img) {
//		int[][] M= new int[img.getWidth()][img.getHeight()];
//		int W = img.getWidth(), H = img.getHeight();
//		for(int i=0; i<W;i++) {
//			for(int j = 0; j<H;j++) {
//				Color c = Misc.buildColor(img.getRGB(i,j));
//				M[i][j]=(c.getRed()+c.getGreen()+c.getBlue())/3;
//			}
//		}
//		return M;
//	}
//	BufferedImage buildFromInts(int[][] src) {
//		int[][] M=new int[src.length][src[0].length];
//		
//		BufferedImage img = new BufferedImage(src.length,src[0].length, BufferedImage.TYPE_INT_ARGB);
//		int z;
//		for(int i=0;i<M.length;i++) {
//			for(int j=0;j<M[0].length;j++) {
//				z=Math.abs(src[i][j])%255;
//				img.setRGB(i,j, Misc.buildColor(new Color(z,z,z,255)));
//			}
//		}
//		return img;
//	}
//	@Override
//	public BufferedImage applyTransformation(BufferedImage src) {
//		int[][]d = get1DerivativeB(src);
//		int[][]d2=get1DerivativeB(d);
//		BufferedImage img= buildFromInts(d2);
//		LogarithmTrans t = new LogarithmTrans();
//		
//		return t.applyTransformation(img); 
//	}
//	public int[][] get1DerivativeA(BufferedImage src) {
//		int[][] M=new int[src.getWidth()][src.getHeight()];
//		int x_0, x_1;
//		for(int i = 0; i<src.getWidth()-1; i++) {
//			for(int j=0; j<src.getHeight()-1; j++) {
//				Color in = Misc.buildColor(src.getRGB(i,j));
//				x_0=colorToInt(in);
//				if(direction==dir_row) 
//					x_1=colorToInt(Misc.buildColor(src.getRGB(i+1,j)));
//				else 
//					x_1=colorToInt(Misc.buildColor(src.getRGB(i,j+1)));					
//				M[i][j]=x_1-x_0;
//			}
//		}
//		return M;
//	}
//	public int[][] get1DerivativeB(BufferedImage src) {
//		int[][] M=new int[src.getWidth()][src.getHeight()];
//		int x_0, x_1;
//		for(int i = 1; i<src.getWidth()-1; i++) {
//			for(int j=1; j<src.getHeight()-1; j++) {
//				Color in = Misc.buildColor(src.getRGB(i,j));
//				x_0=colorToInt(in);
//				if(direction==dir_row) 
//					M[i][j]=getInt(src,i+1,j)/2 - getInt(src,i-1,j) /2;					
//				else 
//					M[i][j]=getInt(src,i,j+1)/2 - getInt(src,i,j-1) /2;
//			}
//		}
//		return M;
//	}
//	public int[][] get1DerivativeB(int[][]src) {
//		int[][] M=new int[src.length][src[0].length];
//		for(int i = 1; i<src.length-1; i++) {
//			for(int j=1; j<src[0].length-1; j++) {
//				if(direction==dir_row) 
//					M[i][j]=M[i+1][j]/2 - M[i-1][j] /2;					
//				else 
//					M[i][j]=M[i][j+1]/2 - M[i][j-1] /2;
//			}
//		}
//		return M;
//	}
//	static boolean validCoords(int x, int y, int w, int h) {
//		return x<w&&x>=0&&y>=0&&y<h;
//	}
//	int colorToInt(Color c) {
//		return c.getRed()+c.getBlue()+c.getGreen()+c.getAlpha();
//	}
//	int getInt(BufferedImage img, int x, int y) {
//		return colorToInt(Misc.buildColor(img.getRGB(x,y)));
//	}
//
//
//	@Override
//	public String shortDescription() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public static void main(String [] a) {
//		Derivative1Trans t = new Derivative1Trans();	
//		new TestImageTransformation(t).run();
//	}
//
//}
