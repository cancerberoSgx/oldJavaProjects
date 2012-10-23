package org.sgx.pickturemakeup.transformations;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.sgx.pickturemakeup.model.ImageTransformation;
import org.sgx.pickturemakeup.test.TestImageTransformation;
import org.sgx.pickturemakeup.transformations.pixel.LogarithmTrans;



public class CompositeTransformation extends ImageTransformation {

	List<ImageTransformation> transf=new LinkedList<ImageTransformation>();
	
	public boolean add(ImageTransformation e) {
		return transf.add(e);
	}

	@Override
	public BufferedImage applyTransformation(BufferedImage src) {
		Iterator<ImageTransformation> i=transf.iterator();
		BufferedImage out=src;
		while(i.hasNext()) {
			out=i.next().applyTransformation(out);
		}
		return out;
	}

	@Override
	public String shortDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** idea : Canny Edge Detector */
	public static void main(String [] a) {
		CompositeTransformation t = new CompositeTransformation();		
		GaussianSmoothTransformation gauss=new GaussianSmoothTransformation();
		RobertCrossTransformation robert=new RobertCrossTransformation();
		gauss.setDeviation(2f);
		gauss.setSize(3);
		robert.setSize(2);
		robert.setFactor(1);
		t.add(gauss);
		t.add(robert);
		new TestImageTransformation(t).run();
	}

}
