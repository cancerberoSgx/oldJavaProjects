package org.sgx.jmencoder.desktrec;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.sgx.swing.utils.ImageUtils;

/**
 * once I start I will take pictures of a desktop area every timeStep ms and
 * save erach frame them into workingDir in png format with the names 1.png,
 * 2.png, etc
 * 
 * note that this class has 2 threads level: one thread (itself) for the recording (many screenshots)
 * and other subthread for single screenshot capture
 * 
 * @author sgurin
 * 
 */
public class Recorder extends Thread {
	private static final String OUTPUT_FORMAT = "png";

	/**
	 * time between 2 frames
	 */
	int timeStep;

	File workingDir;

	Rectangle recordArea;
	/** concurrent flags */
	private volatile boolean stopped = false, paused = false;

	public Recorder(int timeStep, File workingDir, Rectangle recordArea) {
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
		long t1, t2, t3, timeToWait=timeStep;
		while(!stopped){
			if(!paused) {
				t1 = System.currentTimeMillis();
				//take screenshot
				File framef = new File(workingDir.getAbsoluteFile()+
						File.separator+frameCount+"."+OUTPUT_FORMAT);
				System.out.println();
				
					//todo en uno:
//					ImageUtils.getInstance().takeDesktopScreenshotAndDrawMousePointer(
//							recordArea, OUTPUT_FORMAT, framef);
					
					/*saco el screenshot en este hilo (para coordinación temporal correcta en el video) 
					pero escribo la imágen a fs (que demora) en un thread  hijo (2do nivel de threads) */
					BufferedImage buffy = ImageUtils.getInstance().takeDesktopScreenshotAndDrawMousePointer(recordArea, OUTPUT_FORMAT);
					long tt2 = System.currentTimeMillis(), tt3;					
					System.out.print(" buffy created in "+(tt2-t1)+"ms");										
					new ImageWriter(buffy, framef).start();//TODO: do this concurrent task a better plan

				
				t2=System.currentTimeMillis();
				t3=t2-t1; //this time is already counting in the screenshot!
				if(t3>=timeStep) 
					timeToWait=0; //OOPS : the screenshot take more time than timeStep !
				else 
					timeToWait = timeStep-t3;				
				
				//debug
				
				System.out.print(" - whole task completed in : "+t3+"ms "+" time to wait: "+timeToWait+"ms");
				
			}
			if(timeToWait>0)
				Thread.sleep(timeToWait);
			frameCount++;
		}
	}

	public boolean isRecordingPaused() {
		return paused;
	}
	
	class ImageWriter extends Thread {
		BufferedImage buffy;
		 File framef;
		
		public ImageWriter(BufferedImage buffy, File framef) {
			this.buffy=buffy;
			this.framef=framef;
		}
		@Override
		public void run() {
			super.run();
			try {
				ImageUtils.getInstance().write(buffy, OUTPUT_FORMAT, framef);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
