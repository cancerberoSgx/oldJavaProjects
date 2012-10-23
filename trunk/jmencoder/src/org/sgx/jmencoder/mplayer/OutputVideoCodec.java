package org.sgx.jmencoder.mplayer;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author sgurin
 *
 */
public class OutputVideoCodec {
	
	public static OutputVideoCodec COPY() {
		return new OutputVideoCodec(copy);
	}
	
	/** no encoding, just streamcopy */
	public static final String copy = "copy";
	/**special audio-only file for 3-pass encoding, see DOCS.*/
	public static final String frameno = "frameno";
	/**uncompressed video. Use fourcc option to set format explicitly.*/
	public static final String raw = "raw";
	/**libavcodec codecs - best quality!*/
	public static final String lavc = "lavc";
	/**VfW DLLs*/
	public static final String vfw = "vfw";
	/**QuickTime DLLs, currently only SVQ1/3 are supported.*/
	public static final String qtvideo = "qtvideo";
	/**XviD encoding*/
	public static final String xvid = "xvid";
	/**H.264 encoding*/
	public static final String x264 = "x264";
	
public static final String []encoders = {copy, frameno, raw, lavc, lavc, vfw, qtvideo, xvid, x264};  
	
	public static Map<String, String> encDescriptions;
	static {		
		encDescriptions=new HashMap<String, String>();
		encDescriptions.put(copy, "No encoding, just strea");
	}
	
	
	String codec;

	public OutputVideoCodec(String codec) {
		super();
		this.codec = codec;
	}
}
