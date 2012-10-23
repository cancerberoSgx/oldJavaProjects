package org.sgx.j2s.raphael.test;

import org.sgx.j2s.raphael.Element;
import org.sgx.j2s.raphael.Paper;
import org.sgx.j2s.raphael.Path;
import org.sgx.j2s.utils.AbstractRunnable;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.Color;


public class PathTest1 {

	public static void main(String[] args) {
		Paper paper = new Paper(20,20,500,500);
		Path path = paper.path(new Object[][]{ {"stroke", "#036"} }).
			moveTo(10, 10).lineTo(50, 50).cplineTo(100,400).cplineTo(300,220).andClose();
		
		Path p2 = paper.path(new Object[][]{ {"stroke", Color.RED.toCSS()} }).
			moveTo(80, 20).lineTo(50, 50).cplineTo(200,300).cplineTo(200,330).andClose();
		
		Path p3 = paper.path(new Object[][]{ {"stroke", Color.GREEN.toCSS()}, {"stroke-width","8" }, {"stroke-dasharray", "18, 4, 4, 18"} }).
			moveTo(80, 20).curveTo(80,100, 70,90,120,160).curveTo(0,100, 10,190,120,260).curveTo(100,0,200,30,250,120).andClose();
				    	
		
//		Utils.getInstance().installTimer(new AbstractRunnable<Element>() {
//			public void run() {
//			}			
//		}, 1000);
	}

}
