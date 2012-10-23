package org.sgx.jmencoder.mplayer.videoInfoDetector;

import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.ExecutorListener;


public class VideoInfoExecutorListener implements ExecutorListener {
	
	VideoInfoListener l;
	String s="";
	 public VideoInfoExecutorListener(VideoInfoListener l){
		 this.l=l;
	 }
	public void notifyEnd(EndExecutionInformation endExecutionInformation) {
		String [] a = s.split("\n");
		MediaInfo mi = MediaInfo.buildFrom(a);
		l.notifyVideoInfo(mi);
	}
	public void notifyError(String str) {
	}
	public void notifyMovieHeader(String movieHeader) {
	}
	public void notifyOuput(String str) {
		s+=str+"\n";
	}
	
}
