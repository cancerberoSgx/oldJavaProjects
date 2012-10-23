package org.sgx.jmencoder.mplayer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sgurin
 *
 */
public class OutputAudioCodec {
	
	public static OutputAudioCodec COPY() {
		return new OutputAudioCodec(copy);
	}

	/** no encoding, just streamcopy */
	public static final String copy = "copy";
	/**	Encode to uncompressed PCM. */
	public static final String pcm = "pcm";
	/** Encode to MP3 (using LAME).*/
	public static final String mp3lame = "mp3lame";
	/**	Encode with a libavcodec codec*/
	public static final String lavc = "lavc";
	/**Twolame MP2 audio encoder*/
	public static final String twolame = "twolame";
	/**	FAAC AAC audio encoder*/
	public static final String faac = "faac";
	
	public static final String []encoders = {copy, pcm, mp3lame, lavc, twolame, faac};  
	
	public static Map<String, String> encDescriptions;
	static {		
		encDescriptions=new HashMap<String, String>();
		encDescriptions.put(copy, "No encoding, just streamcopy without re-encoding (useful for AC3)");
		encDescriptions.put(pcm,"Uncompressed PCM audio");
		encDescriptions.put(mp3lame, "Encode to MP3 (using LAME). ");
		encDescriptions.put(lavc, "FFmpeg audio encoder (MP2, AC3, ...)");
		encDescriptions.put(twolame, "Twolame MP2 audio encoder");
		encDescriptions.put(faac, "FAAC AAC audio encoder");
	}
	String codec;

	public OutputAudioCodec(String codec) {
		super();
		this.codec = codec;
	}

	public String getCodec() {
		return codec;
	}

	public void setCodec(String codec) {
		this.codec = codec;
	}
	
}
