package org.sgx.j2s.raphael.test;

import org.sgx.j2s.raphael.Element;
import org.sgx.j2s.raphael.Paper;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.Color;


public class TimedEllipse implements Runnable{
	
	Element ellipse;
	private int degrees;
	long timeStep;
	

	public TimedEllipse(Element ellipse, int degrees, long timeStep) {
		super();
		this.ellipse = ellipse;
		this.degrees = degrees;
		this.timeStep = timeStep;
	}


	public void run() {
		ellipse.rotate(degrees);
		Utils.getInstance().installTimer(this, timeStep);
	}
	
	public static void main(String[] args) {
//		Utils.getInstance().loadScript("raphael.js");
		int W = 500, H = 600;
		Paper paper = new Paper(30,30,W,H);
		Element e1 = paper.ellipse(30,30, 20, 30);
		e1.setAttr("fill", Color.BLUE.toCSS());//);
		Runnable runnable = new TimedEllipse(e1, 3, 200);	
		runnable.run();
	}


}
