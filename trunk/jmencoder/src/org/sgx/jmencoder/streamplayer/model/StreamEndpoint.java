package org.sgx.jmencoder.streamplayer.model;

/**
 * model object representing an line streaming server (radio, tv, etc)
 * 
 * @author sgurin
 * 
 */
public class StreamEndpoint extends StreamElement {
	public static final String PROP_URL = "url";

	public StreamEndpoint(String name, String description, String image,
			String url) {
		super(name, description, image);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	String url;

	@Override
	public void dump(StringBuffer b) {
		b.append("Endpoint(" + name + ")");
	}
}
