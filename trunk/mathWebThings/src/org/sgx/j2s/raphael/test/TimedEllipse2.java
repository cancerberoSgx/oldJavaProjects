package org.sgx.j2s.raphael.test;

import org.sgx.j2s.raphael.Element;
import org.sgx.j2s.raphael.Paper;
import org.sgx.j2s.utils.AbstractRunnable;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.Color;


public class TimedEllipse2 {
	
	public static void main(String[] args) {
		int W = 500, H = 600;
		Paper paper = new Paper(30,30,W,H);
		Element e1 = paper.ellipse(30,30, 20, 30);
		e1.setAttr("fill", Color.BLUE.toCSS());//);
		
		AbstractRunnable<Element> changeBgRunnable = new AbstractRunnable<Element>(e1){
			public void run() {
				Utils.getInstance().installTimer(this, 200);
				Color c = new Color(Utils.randomNumberBetween(0, 255), 
						Utils.randomNumberBetween(0, 255),
						Utils.randomNumberBetween(0, 255));
				this.getData().setAttr("fill", c.toCSS());
			}
		};
		
		AbstractRunnable<Element> rotationRunnable = new AbstractRunnable<Element>(e1){
			public void run() {
				Utils.getInstance().installTimer(this, 100);
				int degrees = 4;//Utils.randomNumberBetween(0, 100);
				this.getData().rotate(degrees);
			}
		};
		
		
		changeBgRunnable.run();
		rotationRunnable.run();
	}


}

