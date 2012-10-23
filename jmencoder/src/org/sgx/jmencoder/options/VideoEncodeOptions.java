package org.sgx.jmencoder.options;

import java.io.File;
/**
 * 
 * @author sgurin
 *
 */
public class VideoEncodeOptions {
	/* mandatory attribtutes */
	
	File videoFile, outFile;

	/* non mandatory attributes (can be null) */
	SubtitleOption subOption;
	
VideoFormatOptions videoFormatOption;

	public VideoEncodeOptions(VideoFormatOptions videoFormatOption, 
			SubtitleOption subOption, File videoFile, File outFile) {
		super();
		this.videoFormatOption=videoFormatOption;
		this.videoFile = videoFile;
		this.outFile = outFile;
		this.subOption=subOption;
	}

	public File getOutFile() {
		return outFile;
	}

	public SubtitleOption getSubOption() {
		return subOption;
	}

	public File getVideoFile() {
		return videoFile;
	}

	public VideoFormatOptions getVideoFormatOption() {
		return videoFormatOption;
	}

	
}
