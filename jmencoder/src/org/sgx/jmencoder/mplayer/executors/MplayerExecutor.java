package org.sgx.jmencoder.mplayer.executors;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.jmencoder.options.VideoEncodeOptions;
import org.sgx.jmencoder.options.VideoReproductionOptions;
import org.sgx.jmencoder.streamplayer.ModelUtils;
import org.sgx.jmencoder.streamplayer.ConnectionUtils.ReadUrlListener;
import org.sgx.utils.Utils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.ExecutorListener;

public class MplayerExecutor implements ExecutorListener {
	/**
	 * how much memory (in kBytes) to use when precaching a file or URL when playing streams
	 */
	private static final int DEFAULT_STREAM_PLAY_CACHE = 200;
	protected Executor  e=null;
	protected volatile boolean executing;
	
	//mplayer commands
	List<MPlayerCommandListener> mplisteners = new LinkedList<MPlayerCommandListener>();
	private boolean debug;
//	private boolean plsFound;
	static String [] commandsOutputIds ={"ANS_percent_pos"};
	static Map<String, String> commandValues = new HashMap<String, String>();
	public void addMplayerCommandListener(MPlayerCommandListener l) {
		mplisteners.add(l);
	}
	public void removeMplayerCommandListener(MPlayerCommandListener l) {
		mplisteners.remove(l);
	}
	
	public MplayerExecutor() {
		executing=false;
	}
	
	
//	/**
//	 * selected file must be VIDEO_TS directory
//	 */
//	public void openDVD(VideoReproductionOptions options)throws IOException {
//				
//		//evious version of this operation:
//		String subFont = options.getSubOption().getFont().getFontFamily().getFontFamily();
//		if(!executing) {/*-vo directx:noaccel*/
//			String cmd = MPlayerUtils.mplayerExe+" -slave -framedrop -nocache "+
//				" dvd://1 -dvd-device d:\\  "+
//			(subFont==null?"":(" -font \""+subFont+"\" "))+
//			//" -slang es -subcp latin1 "+			
//			"";			
//			System.out.println("executing: "+cmd);
//			e = MencoderExecutorImpl.execute(cmd, this);
//			executing=true;
//		}
//	}
	
	public void encode(VideoEncodeOptions options) throws IOException {
		File f = options.getVideoFile(), out = options.getOutFile();
		if(!executing) {
			String cmd = MPlayerUtils.mencoderExe+"  ";
			
				cmd+=Utils.quotePath(f.getAbsolutePath())+" " +
					" -o "+Utils.quotePath(out.getAbsolutePath())+" ";
			
			//subtitle
			
			cmd+=OptionsExecutor.getSubtitleCommand(options.getSubOption());
			
			System.out.println("executing: "+cmd);
			e = MencoderExecutorImpl.execute(cmd, this);
			executing=true;
		}
//			throw new RuntimeException("busy. please instantiate another object");
		
	}
	public void playVideo(VideoReproductionOptions options) throws IOException {
		File f = options.getVideoFile();
		if(!executing) {/*-vo directx:noaccel*/
			String cmd = MPlayerUtils.mplayerExe+" -slave ";
				
			if(f.isDirectory())  //dvd
				cmd+=" dvd://1 -dvd-device "+Utils.quotePath(f.getAbsolutePath())+" ";
			
			else  //regular file
				cmd+=" "+Utils.quotePath(f.getAbsolutePath())+" " ;
			
			cmd+= OptionsExecutor.getSubtitleCommand(options.getSubOption());			
			
			System.out.println("executing2: "+cmd);
			e = MencoderExecutorImpl.execute(cmd, this);	
			executing=true;
		}		
	}
	
	
	public void enterCommand(String cmd) throws IOException {
		if(e!=null)
			e.writeAndFlushLn(cmd);
	}

	public void notifyEnd(EndExecutionInformation endExecutionInformation) {
//		System.out.println("End mplayer");
		executing=false;
		
	}

	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	public void notifyError(String str) {
		if(debug) {
			System.out.println("err: "+str);
		}
	}

	public void notifyOuput(String str) {
		if(debug) {
			System.out.println("out: "+str);
		}
		int b = str.indexOf("="), a = str.indexOf("ANS_");
		if(a!=-1 && b!=-1) {
			String id = str.substring(a+4, b), 
				val = str.substring(b+1, str.length());
			for(MPlayerCommandListener l : mplisteners)
				l.notifyCommandOutput(id, val);
		}	
	}

	public boolean isExecuting() {
		return executing;
	}	
	public void setMplayerProperty(String prop, String value) throws IOException {
		enterCommand("set_property "+prop+" "+value);
	}
	public void notifyMovieHeader(String movieHeader) {		
	}
	

	public void exit() {
		try {
			if(e!=null) {
				e.getProc().destroy();
			}
		} catch (Exception e) {
			
		}
		
	}
}
