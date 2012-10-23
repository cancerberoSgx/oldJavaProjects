package org.sgx.jmencoder.mplayer.executors;
import java.io.File;
import java.io.IOException;

import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.ExecutorListener;


public class ConsoleUtils {
	public static void playDvdFromFolder(String path, int movie, ExecutorListener l) throws IOException {
		Executor e = ExecutorFactory.getDefaultExecutor();
		e.addListener(l);
		String dev = "";
		if(path!=null) {
			dev=" -dvd-device \""+path+"\"";
		}
//		String cmd="mplayer\\mplayer.exe dvd://"+movie+dev;
		String cmd = MPlayerUtils.mplayerExe+" -ovc help";
		System.out.println(cmd);
		e.runCommand(cmd);
	}
	
	public static void execute(String cmd ) throws IOException {
		Executor e = ExecutorFactory.getDefaultExecutor();
		System.out.println("Executing: "+cmd);
		e.addListener(new ExecutorListener() {
			public void notifyEnd(EndExecutionInformation endExecutionInformation) {
				System.out.println("End execution");
			}
			public void notifyError(String str) {
				System.out.println("error: "+str);
			}
			public void notifyMovieHeader(String movieHeader) {
//				System.out.println("movieHeader: "+movieHeader);
			}
			public void notifyOuput(String str) {
				System.out.println("out: "+str);
			}
			
		});
		System.out.println(cmd);
		e.runCommand(cmd);
	}
	
	public static void testdvdplay() {
		try {
			playDvdFromFolder(null, 1, new ExecutorListener() {
				public void notifyEnd(
						EndExecutionInformation endExecutionInformation) {
					System.out.println(endExecutionInformation);
				}

				public void notifyError(String str) {
					System.out.println(str);
				}

				public void notifyOuput(String str) {
					System.out.println(str);				
				}

				public void notifyMovieHeader(String movieHeader) {
					// TODO Auto-generated method stub
					
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testExecute() {
		try {
			execute(MPlayerUtils.mencoderExe+" -oac help");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		testExecute();
	}
}