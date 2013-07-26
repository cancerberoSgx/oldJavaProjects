package org.sgx.picturemakeup.transformations;
//package transformations;
//
//import java.awt.Color;
//import java.awt.image.BufferedImage;
//
//
//import org.jscience.mathematics.number.Float64;
//import org.jscience.mathematics.vector.Float64Matrix;
//import org.jscience.mathematics.vector.Float64Vector;
//import org.jscience.mathematics.vector.Matrix;
//import org.jscience.mathematics.vector.Vector;
//
//import test.TestImageTransformation;
//import utils.Misc;
//
//import model.ImageTransformation;
//
//public class InverseTrans extends ImageTransformation {
//
//	@Override
//	public BufferedImage applyTransformation(BufferedImage src) {		
//		Matrix<Float64> M = Float64Matrix.valueOf(getDoubles(src));
//		Vector<Float64>y=M.getColumn(0);
//		M=M.solve(M);
//		return buildFromMatrix(M);
//	}
//
//	double[][] getDoubles(BufferedImage img) {
//		double[][] M= new double[img.getWidth()][img.getHeight()];
//		int W = img.getWidth(), H = img.getHeight();
//		for(int i=0; i<W;i++) {
//			for(int j = 0; j<H;j++) {
//				Color c = Misc.buildColor(img.getRGB(i,j));
//				M[i][j]=((float)(c.getRed()+c.getGreen()+c.getBlue()))/3.0f;
//			}
//		}
//		return M;
//	}
//	
//	BufferedImage buildFromMatrix(Matrix<Float64> M) {
//		BufferedImage img = new BufferedImage(M.getNumberOfColumns(), M.getNumberOfRows(), BufferedImage.TYPE_INT_ARGB);
//		int z;
//		for(int i=0;i<M.getNumberOfColumns();i++) {
//			for(int j=0;j<M.getNumberOfRows();j++) {
//				z=Math.abs(M.get(i,j).intValue()*3)%255;
////				if(z>255)z=255;
////				if(z<0)z=0;
////				z=z%255;
//				img.setRGB(i,j, Misc.buildColor(new Color(((int)(z)), ((int)(z)), ((int)(z)), 255)));
//			}
//		}
//		return img;
//	}
//	@Override
//	public String shortDescription() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	public static void main(String [] a) {
//		InverseTrans t = new InverseTrans();	
//		new TestImageTransformation(t).run();
//	}
//
//}
