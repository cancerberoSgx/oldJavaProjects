package org.sgx.jmencoder.options;

import java.io.File;

public class VideoReproductionOptions {
	SubtitleOption subOption;
	
	File videoFile;

	public SubtitleOption getSubOption() {
		return subOption;
	}

	public VideoReproductionOptions(File videoFile, SubtitleOption subOption) {
		super();
		this.videoFile = videoFile;
		this.subOption=subOption;
	}

	public File getVideoFile() {
		return videoFile;
	}
	
	
}
