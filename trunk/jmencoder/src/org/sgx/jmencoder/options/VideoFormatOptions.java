package org.sgx.jmencoder.options;

public class VideoFormatOptions {
	
	Integer vbitrate, abitrate;
	
	/** video size. if null video scale information will not be passed to the encoder (encoder defined behavior))*/
	Integer width, height;
	
	/** these are mplayer video format concepts */
	String outputContainer, vcodec;
	
	/** this are non - advanced video format concepts */
	String format;
	
	
	public VideoFormatOptions(Integer vbitrate, Integer abitrate, Integer width, Integer height) {
		super();
		this.vbitrate = vbitrate;
		this.abitrate = abitrate;
		this.width = width;
		this.height = height;
	}
	

	public Integer getAbitrate() {
		return abitrate;
	}

	public void setAbitrate(Integer abitrate) {
		this.abitrate = abitrate;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getOutputContainer() {
		return outputContainer;
	}

	public void setOutputContainer(String outputContainer) {
		this.outputContainer = outputContainer;
	}

	public Integer getVbitrate() {
		return vbitrate;
	}

	public void setVbitrate(Integer vbitrate) {
		this.vbitrate = vbitrate;
	}

	public String getVcodec() {
		return vcodec;
	}

	public void setVcodec(String vcodec) {
		this.vcodec = vcodec;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}


	
//	Double afps, vfps; //I wander...
}
