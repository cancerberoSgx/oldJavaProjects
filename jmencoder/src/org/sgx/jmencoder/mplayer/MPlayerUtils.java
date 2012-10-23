package org.sgx.jmencoder.mplayer;

import java.io.File;
import java.io.IOException;

import org.sgx.jmencoder.mplayer.executors.ConsoleUtils;
import org.sgx.utils.FSUtils;

public class MPlayerUtils {

	public static String ffmpegExe = null;	
	public static String mencoderExe = null;
	public static String mplayerExe = null;
	
	public static void setupMencoder() throws Exception {
		ffmpegExe = guessFFMpegExe();	
		mencoderExe = guessMencoderExe();
		mplayerExe = guessMPlayerExe();
	}
	
	static {
		try {
			setupMencoder();
			System.out.println("mplayer bin is in : "+mplayerExe);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static String guessFFMpegExe() throws Exception {
		if(File.separator.equals("\\"))
			return "ffmpeg" + File.separator + "ffmpeg.exe"; // @jve:decl-index=0:;
		else { //linux : search PATH env var for locating mplayer
			String programPath = FSUtils.which("ffmpeg", null);
			if(programPath==null)
				throw new Exception("couln't find ffmpeg in system PATH");
			else
				return programPath;			
		}		
	}
	private static String guessMencoderExe() throws Exception {
		if(File.separator.equals("\\"))
			return "mplayer" + File.separator + "mencoder.exe"; // @jve:decl-index=0:;
		else { //linux : search PATH env var for locating mplayer
			String programPath = FSUtils.which("mencoder", null);
			if(programPath==null)
				throw new Exception("couln't find mencoder in system PATH");
			else
				return programPath;			
		}	
	}
	private static String guessMPlayerExe() throws Exception {
		if(File.separator.equals("\\"))
			return "mplayer" + File.separator + "mplayer.exe"; // @jve:decl-index=0:;
		else { //linux : search PATH env var for locating mplayer
			String programPath = FSUtils.which("mplayer", null);
			if(programPath==null)
				throw new Exception("couln't find mplayer in system PATH");
			else
				return programPath;			
		}			
	}
	
	
//	/**
//	 * 
//	 options=${3:-"-oac mp3lame"}
//	 * 
//	 * mencoder -demuxer rawvideo -rawvideo w=1:h=1 -ovc copy -of rawaudio
//	 * -endpos `mplayer -identify $1 -frames 0 2>&1 | grep ID_LENGTH | cut -d
//	 * "=" -f 2` -audiofile $1 -o $2 $options $1
//	 * 
//	 * @param in
//	 * @param out
//	 */
//	public static void transformToMp3(File in, File out) {
//		String cmd = MencoderUtils.mencoderExe+" -demuxer rawvideo -rawvideo w=1:h=1" +
//				" -ovc copy -of rawaudio -audiofile "+
//				" \""+in.getAbsolutePath()+"\""+
//				" -o "+" \""+in.getAbsolutePath()+"\""+
//				" -oac mp3lame "+
//				" \""+in.getAbsolutePath()+"\""+
//				"";
//		try {
//			ConsoleUtils.execute(cmd);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public static void transformToMp3Test() {
//		String in = "filesTest\\in.mp3", out="in_out.mp3";
//		transformToMp3(new File(in), new File(out));
//	}
//	public static void main(String[] args) {
//		transformToMp3Test();
//	}

	public static String getAppopiateFileExtensionFor(String oc) {
		if(oc.equals(Format.AVI))
				return "avi";
		else if(oc.equals(Format.MPEG))			
				return "mpg";
		else if(oc.equals(Format.MPEG_4))
				return "avi";
		else throw new RuntimeException("outputcontainer not recognized");
	}
}
