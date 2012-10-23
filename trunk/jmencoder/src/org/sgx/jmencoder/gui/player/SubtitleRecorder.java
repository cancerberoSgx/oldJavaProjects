package org.sgx.jmencoder.gui.player;

import java.io.IOException;

import org.sgx.jmencoder.mplayer.executors.MplayerExecutor;

public class SubtitleRecorder {

	boolean recording;
	
	MplayerExecutor executor;

	public SubtitleRecorder(MplayerExecutor executor) {
		super();
		this.executor = executor;
	}
	
//	public void record() {
//		try {
//			String streamPosStr = executor.getMplayerProperty("stream_pos"),
//			
//				timePosStream = executor.getMplayerProperty("time_pos");
//			
//			executor.readSubLog();
//			System.out.println("jajaj: "+ timePosStream+" - temp: "+MplayerUtils.formatTimeToSeconds(timePosStream));
//			executor.getMplayerProperty("sub_log");
//			executor.enterCommand("sub_log");
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public void pause() {
//		
//	}
}
