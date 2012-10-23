package org.sgx.jmencoder.mplayer.videoInfoDetector;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.utils.JavaUtils;
import org.sgx.utils.Predicate;
import org.sgx.utils.Utils;
import org.sgx.utils.exec.Executor;


/**
 * use me as a map. this class is used for video information detection with mplayer. 
 * 
 * @author sgurin
 *
 */
public class MediaInfo extends HashMap<String, String>{

	private static final long serialVersionUID = 1L;
	public static final String ID_FILENAME = "ID_FILENAME";
	public static final String ID_DEMUXER = "ID_DEMUXER";
	public static final String ID_LENGTH = "ID_LENGTH";
	public static final String ID_VIDEO_BITRATE = "ID_VIDEO_BITRATE";
	public static final String ID_VIDEO_WIDTH = "ID_VIDEO_WIDTH";
	public static final String ID_VIDEO_HEIGHT = "ID_VIDEO_HEIGHT";
	public static final String ID_VIDEO_FPS = "ID_VIDEO_FPS";
	public static final String ID_VIDEO_ASPECT = "ID_VIDEO_ASPECT";
	public static final String ID_VIDEO_CODEC = "ID_VIDEO_CODEC";
	public static final String ID_VIDEO_FORMAT = "ID_VIDEO_FORMAT";
	public static final String ID_AUDIO_RATE = "ID_AUDIO_RATE";
	public static final String ID_AUDIO_NCH = "ID_AUDIO_NCH";
	public static final String ID_AUDIO_BITRATE = "ID_AUDIO_BITRATE";
	public static final String ID_AUDIO_CODEC = "ID_AUDIO_CODEC";
	
	public static final String [] PropsIds = {
		ID_FILENAME, 			ID_DEMUXER, 		ID_LENGTH,
		
		ID_VIDEO_BITRATE, 	ID_VIDEO_WIDTH, 	ID_VIDEO_HEIGHT, 
		ID_VIDEO_FPS, 		ID_VIDEO_ASPECT, 	ID_VIDEO_CODEC,
		ID_VIDEO_FORMAT,
		
		ID_AUDIO_RATE, 		ID_AUDIO_NCH, 	 
		ID_AUDIO_BITRATE,  	ID_AUDIO_BITRATE, ID_AUDIO_CODEC};

	public String getName(String id) {
		return id.substring(3,id.length()).toLowerCase();
	}
	
	public String getNiceValue(String id) {
		if(id.equals("ID_LENGTH")) 
			return (Double.parseDouble(get(id))/60)+" min";
		else if(id.equals("ID_VIDEO_FPS")) 
			return get(id)+" fps";
		else if(id.equals("ID_VIDEO_HEIGHT")||id.equals("ID_VIDEO_WIDTH")) 
			return get(id)+" px";
//		else if(id.equals("ID_LENGTH")) 
//			return get(id)+" px";
//		else if(id.equals("ID_LENGTH")) 
//			return " mi";
//		else if(id.equals("ID_LENGTH")) 
//			return " mi";
//		else if(id.equals("ID_LENGTH")) 
//			return " mi";
		
		else 
			return get(id);
	}
	
	public Map<String, String> getNiceRepr() {
		Map<String, String> m = new HashMap<String, String>();
		for(String id : keySet()) {
			m.put(getName(id), getNiceValue(id));
		}
		return m;
	}
	
	
	/**the exact mplayer output lines
	 *  
	 * use for example mplayer/mplayer.exe -msglevel identify=6 -frames 0
	 * 
	 * @param outputLines
	 * @return
	 */
	public static MediaInfo buildFrom(String [] outputLines) {
		MediaInfo mi = new MediaInfo();
		for(int i=0; i<PropsIds.length; i++) {
			final String pid = PropsIds[i];
			Collection s = JavaUtils.select(outputLines, new Predicate<String>() {
				public boolean select(String o) {
					return o.startsWith(pid);						
				}				
			});
			if(s.size()>0) {
				String val = (String)s.toArray()[0];
				val = val.substring(pid.length()+1, val.length());
				mi.put(pid, val);
			}
		}		
		return mi;
	}
	
	public String toString() {
		return Utils.toString(this);
	}
	
	private static Executor executor;

	/** async
	 * @throws IOException */
	public static synchronized void getVideoInfo(File f, VideoInfoListener l) throws IOException {

		String cmd = "mplayer/mplayer.exe -msglevel identify=6 -frames 0 "+
			"\""+f.getAbsolutePath()+"\"";

		 	VideoInfoExecutorListener vil = new VideoInfoExecutorListener(l);
			
			executor = MencoderExecutorImpl.execute(cmd, vil);
			
	}
	
	
	public static boolean workin=false;
	public static MediaInfo m = null;	
	/** sync way */
	public static synchronized MediaInfo getVideoInfo(File f) throws IOException, InterruptedException {

		String cmd = MPlayerUtils.mplayerExe+" -msglevel identify=6 -frames 0 "+
			"\""+f.getAbsolutePath()+"\"";
		 	VideoInfoExecutorListener vil = new VideoInfoExecutorListener(new VideoInfoListener() {
				public void notifyVideoInfo(MediaInfo m) {
					workin=false;
					MediaInfo.m=m;
				}
		 		
		 	});
			workin=true;
			executor = MencoderExecutorImpl.execute(cmd, vil);
			while(workin) {
				Thread.sleep(500);
			}
			
			MediaInfo m1=m;
			m=null;
			workin=false;
			return m1;
			
	}

	public String get(String id, String defaultValue) {
		if(get(id)!=null&&!get(id).equals("")){
			if(id.equals(ID_VIDEO_BITRATE)&&get(id).equals("0"))
				return defaultValue;
			else
				return get(id);
		}
		else
			return defaultValue;
				
	}
}
