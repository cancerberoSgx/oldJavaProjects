package org.sgx.j2s.raphael.test;

import org.sgx.j2s.raphael.Element;
import org.sgx.j2s.raphael.Paper;
import org.sgx.j2s.raphael.Path;
import org.sgx.j2s.utils.AbstractRunnable;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.Color;


public class ImgTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Paper paper = new Paper(20,20,1500,1500);
		Element img = paper.image("cara2.png", 20,20,100,100);
		AbstractRunnable<Element> rotationRunnable = new AbstractRunnable<Element>(img){
			public void run() {
				Utils.getInstance().installTimer(this, 100);
				int degrees = 4;
//				this.getData().rotate(degrees);
				this.getData().scale(1.5, 1.5);
			}
		};
		rotationRunnable.run();
		
//		Path p3 = paper.path(new Object[][]{ {"stroke", Color.GREEN.toCSS()} }).
//		moveTo(80, 20).curveTo(80,100, 70,90,120,160).curveTo(0,100, 10,190,120,260)
//		.curveTo(100,0,200,30,250,120).andClose();
//		
//		AbstractRunnable<Element> pathRotationRunnable = new AbstractRunnable<Element>(p3){
//			public void run() {
//				Utils.getInstance().installTimer(this, 1000);
//				int degrees =5;
//				this.getData().scale(1.5, 1.5);
//			}
//		};
//		pathRotationRunnable.run();
		
//		img.rotate(30);
	}

}
