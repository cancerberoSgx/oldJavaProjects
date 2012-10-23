package org.sgx.jmencoder.mplayer.executors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.ExecutorListener;

public class MencoderExecutorImpl extends Executor/* implements MencoderExecutor*/ {

	private String str;

	private String MENCODER_PROGRESS_PATTERN_STR = 
		"\\s*Pos\\:\\s+(\\S+)\\s+(\\S+)(.....)\\s+(\\S+)\\s+Trem\\:\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*";

	/**
	 * ejemplos:
	 * 
	 * para un flv:
	 * VIDEO:  [FLV1]  320x239  0bpp  1000.000 fps    0.0 kbps ( 0.0 kbyte/s)
	 * 
	 * para un .avi que se ve que es xvid:
	 * VIDEO:  [XVID]  800x336  12bpp  25.000 fps  871.3 kbps (106.4 kbyte/s)
	 * 		25.000 fps (movie frame rate)
	 * 		871.3 kbps (bitrate?)
	 */
	private String MOVIEFRAMERATE_PATTERN  = 
		"";
	/**
	 * 
	 * 	AVI file format detected.
	 */
	private String CONTAINERFORMAT_PATTERN  = 
		"";
	Pattern pat = Pattern.compile(MENCODER_PROGRESS_PATTERN_STR);

	protected boolean localIntError;

	protected String err;

	private Thread stdoutThread;

	private Thread stderrThread;


	public String[] runCommand(String cmd) throws	IOException	{  

		outputList = new ArrayList<String>(); 
		errorList = new ArrayList<String>(); 


		proc = Runtime.getRuntime().exec(cmd);
		instrm = proc.getOutputStream();

		outstrm = proc.getInputStream();

		//reader of the program std output stream
		outstrmbr = new BufferedReader(new InputStreamReader(outstrm));

		//writer to the program's std input stream
		instrmbw = new BufferedWriter(new OutputStreamWriter(instrm));

		errstrm = proc.getErrorStream();
		errstrmbr = new BufferedReader(new InputStreamReader(errstrm));

		stdoutThread = new Thread() {
			public void run() {
				String movieHeader = "";
				boolean doMovieHeader=true;
				try {
					while ( (str = outstrmbr.readLine()) != null) {
						for(ExecutorListener l : getListeners()) {
							l.notifyOuput(str);
						}
						if(doMovieHeader)
							movieHeader+=(str+"\n");
						if(str.startsWith("Pos")) {
							doMovieHeader=false;
							for(ExecutorListener l : getListeners()) 
								l.notifyMovieHeader(movieHeader);
						}
					}
				} catch (IOException e) {
					localIntError=true;
					e.printStackTrace();
				}
				System.out.println("*** End of STDOUT stream reading ***");
			}
		};
		stdoutThread.start();

		stderrThread = new Thread() {
			public void run() {
				try {
					while ( (err = errstrmbr.readLine()) != null) {
						for(ExecutorListener l : getListeners()) {
							l.notifyError(err);
						}
					}
				} catch (IOException e) {
					localIntError=true;
					e.printStackTrace();
//					Thread.currentThread().stop();
				}
				
				System.out.println("*** End of STDERR stream reading ***");
			}
		};
		stderrThread.start();

//		System.err.println("FIN: asdkajsdjshakjshdkaj\nshdkjas\nhdkjjjjjjjjjjjjjjjjj\njjjjjjjjjjj\njjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj\njjjjjjjjjjjjjjjasdkajsdjshakjshdkajshdkjashdkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjasdkajsdjshakjshdkajshdkjashdkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjasdkajsdjshakjshdkajshdkjashdkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjasdkajsdjshakjshdkajshdkjashdkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
		int exitVal=-1;


		if(!localIntError) {
			try { 
				exitVal = proc.waitFor();			
			}
			catch (InterruptedException e) {
				System.err.println("Process was interrupted"); 
			}
		}
		else {
			System.err.println("Local interruption exception");
		}
		proc.destroy();

		outstrmbr.close();
		errstrmbr.close();

		for(ExecutorListener e : getListeners()) {
			e.notifyEnd(new EndExecutionInformation(outputList, errorList, exitVal));
		}

		return (String[])outputList.toArray(new String[0]); 
	}

	public synchronized String getCurrentOutput() {
		return getSetStr(null, false);
	}

	public synchronized String getSetStr(String str, boolean setting) {
		if(setting)
			this.str=str;
		return this.str;			
	}

	/**
	 * the not perfect but working example of calling an app in another thread
	 * @throws IOException
	 */
	public static Executor execute(String cmd, 
			ExecutorListener l) throws IOException	{		
		Executor e = ExecutorFactory.getDefaultExecutor();
		e .addListener(l);
		new Executor.ExecutorThread(cmd, e).start();
		return e;
	}


}
