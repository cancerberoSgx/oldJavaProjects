package org.sgx.picturemakeup.test;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.sgx.picturemakeup.gui.ImageWidgetImpl;
import org.sgx.picturemakeup.gui.editors.PropertHaverEditorPanel;
import org.sgx.picturemakeup.model.ImageTransformation;
import org.sgx.picturemakeup.model.Property;
import org.sgx.picturemakeup.model.PropertyListener;
import org.sgx.picturemakeup.transformations.BlurTransformation;
import org.sgx.picturemakeup.transformations.ConvolutionTransformation;
import org.sgx.picturemakeup.transformations.pixel.DitherColor;
import org.sgx.picturemakeup.transformations.pixel.DitherInverse;
import org.sgx.picturemakeup.transformations.pixel.DitherTransformation;


public class PropHaverEditorTest {
	ImageTransformation t;
	ImageWidgetImpl imgW;
	public void test() {
		
		 imgW = new ImageWidgetImpl(ImageWidgetImpl.defaultImgPath);
		JFrame imgF =  new JFrame("image");
		imgF.setContentPane(imgW);
		imgF.setPreferredSize(new Dimension(imgW.getWidth(), imgW.getHeight()));
		imgF.setBounds(new Rectangle(303,303,300,300));
		imgF.setVisible(true);
		
		t = new ConvolutionTransformation();
		t.register(new PropertyListener() {
			public void notifyPropertyChange(String propId, Object value) {
				PropHaverEditorTest.this.imgW.showTransformation(PropHaverEditorTest.this.t);
			}			
		});
		PropertHaverEditorPanel p = new PropertHaverEditorPanel(t);
		JFrame editorF = new JFrame("editor");
		editorF.setContentPane(p);
		editorF.setBounds(new Rectangle(2,2,300,300));
		editorF.setVisible(true);
		editorF.setPreferredSize(new Dimension(150, 80));
		
	}
	public static void main(String [] a) {
		new PropHaverEditorTest().test();
	
	}
}
