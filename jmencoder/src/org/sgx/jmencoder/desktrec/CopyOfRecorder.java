package org.sgx.jmencoder.desktrec;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import org.sgx.swing.utils.ImageUtils;

/**
 * once I start I will take pictures of a desktop area every timeStep ms and
 * save erach frame them into workingDir in png format with the names 1.png,
 * 2.png, etc
 * 
 * @author sgurin
 * 
 */
public class CopyOfRecorder extends Thread {
	private static final String OUTPUT_FORMAT = "png";

	/**
	 * time between 2 frames
	 */
	int timeStep;

	File workingDir;

	Rectangle recordArea;
	/** concurrent flags */
	private volatile boolean stopped = false, paused = false;

	public CopyOfRecorder(int timeStep, File workingDir, Rectangle recordArea) {
		super();
		this.timeStep = timeStep;
		this.workingDir = workingDir;
		this.recordArea = recordArea;
		stopped=false;
	}
	
	public int getTimeStep() {
		return timeStep;
	}

	public void setTimeStep(int timeStep) {
		this.timeStep = timeStep;
	}

	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	public Rectangle getRecordArea() {
		return recordArea;
	}

	public void setRecordArea(Rectangle recordArea) {
		this.recordArea = recordArea;
	}

	
public void stopRecording() {
	stopped=true;
}
public void togglePauseRecording() {
	paused = !paused;
}

	@Override
	public void run() {		
		super.run();
		try {
			startRecording();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startRecording() throws AWTException, IOException, InterruptedException {
		int frameCount=0;
		long t1 = System.currentTimeMillis(), t2, t3, timeToWait=timeStep;
		while(!stopped){
			if(!paused) {
				
				//take screenshot
				File framef = new File(workingDir.getAbsoluteFile()+
						File.separator+frameCount+"."+OUTPUT_FORMAT);
				ImageUtils.getInstance().takeDesktopScreenshotAndDrawMousePointer(
						recordArea, OUTPUT_FORMAT, framef);
				
				
				t2=System.currentTimeMillis();
				t3=t1-t2; //this time is already counting in the screenshot!
				if(t3>=timeStep) {
					timeToWait=0; //OOPS : the screenshot take more time than timeStep !
					System.out.println("OOPS : the screenshot take more time than timeStep !");
				}
				else {
					timeToWait = timeStep-t3;
				}
				
				//debug
				
				System.out.println("screenshot taked in : "+(t2-t1)+"ms");
				
			}			
			Thread.sleep(timeToWait);
			frameCount++;
		}
	}

	public boolean isRecordingPaused() {
		return paused;
	}
}
