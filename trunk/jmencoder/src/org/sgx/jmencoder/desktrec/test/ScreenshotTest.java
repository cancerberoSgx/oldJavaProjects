package org.sgx.jmencoder.desktrec.test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenshotTest {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		   try {
		        Robot robot = new Robot();
			Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		        BufferedImage bufferedImage = robot.createScreenCapture(captureSize);
		        
		        
		    }
		    catch(AWTException e) {
		    	System.err.println("Someone call a doctor!");
		    }

	}

}
