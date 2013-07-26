package org.sgx.picturemakeup.test;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.sgx.picturemakeup.gui.ImageWidgetImpl;
import org.sgx.picturemakeup.model.ImageTransformation;
import org.sgx.picturemakeup.model.Property;
import org.sgx.picturemakeup.transformations.HighPassTransformation;
import org.sgx.picturemakeup.transformations.morphologic.DilationTrans;
import org.sgx.picturemakeup.transformations.pixel.DitherInverse;
import org.sgx.picturemakeup.transformations.pixel.DitherTransformation;
import org.sgx.picturemakeup.transformations.pixel.LogarithmTrans;



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
		ImageWidgetImpl orig = new ImageWidgetImpl(ImageWidgetImpl.getDefaultImg());
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
