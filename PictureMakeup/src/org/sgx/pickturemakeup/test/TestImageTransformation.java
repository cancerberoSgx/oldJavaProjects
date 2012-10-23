package org.sgx.pickturemakeup.test;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.sgx.pickturemakeup.gui.ImageWidgetImpl;
import org.sgx.pickturemakeup.model.ImageTransformation;
import org.sgx.pickturemakeup.model.Property;
import org.sgx.pickturemakeup.transformations.HighPassTransformation;
import org.sgx.pickturemakeup.transformations.morphologic.DilationTrans;
import org.sgx.pickturemakeup.transformations.pixel.DitherInverse;
import org.sgx.pickturemakeup.transformations.pixel.DitherTransformation;
import org.sgx.pickturemakeup.transformations.pixel.LogarithmTrans;



public class TestImageTransformation {
	ImageTransformation t;
//	

	public TestImageTransformation(ImageTransformation t) {
		super();
		// TODO Auto-generated constructor stub
		this.t = t;
	}
	
	public void run() {
		JFrame origF=new JFrame("original");
		ImageWidgetImpl orig = new ImageWidgetImpl(ImageWidgetImpl.defaultImgPath);
		origF.setContentPane(orig);
		origF.setPreferredSize(new Dimension(orig.getWidth(), orig.getHeight()));
		origF.setVisible(true);
		origF.setBounds(new Rectangle(50,10,200,200));
		
		JFrame destF = new JFrame("transformed");
		ImageWidgetImpl dest = new ImageWidgetImpl(t.applyTransformation(orig.getBufferredImage()));
		destF.setContentPane(dest);
		destF.setPreferredSize(new Dimension(150, 80));
		destF.setBounds(new Rectangle(20,20,200,200));
		destF.setVisible(true);
	}
	
	public static void main(String [] a) {
		ImageTransformation t = new LogarithmTrans();
		new TestImageTransformation(t).run();
	}
}
