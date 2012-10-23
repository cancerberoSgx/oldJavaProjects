package org.sgx.jmencoder.desktrec;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

public class RecorderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			test1();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void test1() throws InterruptedException, AWTException, IOException {
		File workingDir = new File("/home/sebastian/borrar");
		int timeStep = 300;
		Rectangle recordArea = new Rectangle(5,5,300,300);
		Recorder shoter = new Recorder(timeStep, workingDir, recordArea);
		shoter.start();
		Thread.sleep(2000);
		System.out.println("stopping");
		shoter.stopRecording();
//		shoter.stop();
	}

}
