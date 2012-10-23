package org.sgx.j2s.raphael.test;

import org.sgx.j2s.raphael.Element;
import org.sgx.j2s.raphael.Paper;
import org.sgx.j2s.raphael.Path;
import org.sgx.j2s.utils.AbstractRunnable;
import org.sgx.j2s.utils.Utils;


public class DrawingTest {

	public static void main(String[] args) {
		Paper paper = new Paper(30,30,500,500);		
		Paper group = paper.group();
		

//		/**
//		 * @j2sNative
//		 * debugger;
//		 */{}
		 
		Path p1 = group.path(new Object[][]{{"stroke","#05a"}, {"stroke-width","8px"}, {"stroke-linecap", "round"}})
			.moveTo(20, 80)
		    .relatively()
		    .lineTo(0, -50)
		    .addRoundedCorner(16, "ur")
		    .addRoundedCorner(16, "rd")
		    .lineTo(0, 50)
		    .moveTo(0, -50)
		    .addRoundedCorner(16, "ur");
		
		Path p2 = group.path(new Object[][]{{"stroke","#8af"},{"stroke-width", "8px"}, {"stroke-linecap", "round"}})
		    .moveTo(82, 80)
		    .relatively()
		    .lineTo(0, -50)
		    .addRoundedCorner(16, "ur");
		
		AbstractRunnable<Path> rotationRunnable = new AbstractRunnable<Path>(p2){
		public void run() {
			Utils.getInstance().installTimer(this, 200);
			getData().rotate(5);
		}
	};
	rotationRunnable.run();
	}
	
}
