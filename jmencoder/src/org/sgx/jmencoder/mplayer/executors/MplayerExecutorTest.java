package org.sgx.jmencoder.mplayer.executors;

import java.io.File;
import java.io.IOException;

import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.utils.Utils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.ExecutorListener;

public class MplayerExecutorTest implements ExecutorListener {

	public static void main(String[] args) {
		try {
			new MplayerExecutorTest().testOpenDVD();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void testOpenDVD() throws IOException {
		File f = new File("/media/Disc_/VIDEO_TS");		
		if(!f.isDirectory()) {
			throw new RuntimeException("selected file must be VIDEO_TS directory");
		}		
		
		String cmd = MPlayerUtils.mplayerExe+" "+
			" dvd://1 "+//-dvd-device "+Utils.quotePath(f.getAbsolutePath())+
		"";			
		System.out.println("executing: "+cmd);
		Executor e = MencoderExecutorImpl.execute(cmd, this);
		
	}

	@Override
	public void notifyEnd(EndExecutionInformation endExecutionInformation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyError(String str) {
		System.out.println("ERR: "+str);
	}

	@Override
	public void notifyMovieHeader(String movieHeader) {
		System.out.println("movieHeader: "+movieHeader);
		
	}

	@Override
	public void notifyOuput(String str) {
		System.out.println("out: "+str);
	}

}
