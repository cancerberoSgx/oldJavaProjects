package org.sgx.jmencoder.streamplayer.model;

import java.util.List;

public class StreamDocument extends StreamElement{

	public void setNodes(List<StreamElement> nodes) {
		this.nodes = nodes;
	}

	public List<StreamElement> getNodes() {
		return nodes;
	}

	public StreamDocument(String name, String description, String image) {
		super(name, description, image);
	}
	
	List<StreamElement> nodes;

	public void dump(StringBuffer b) {		
		b.append("-document-");
		for(StreamElement el : nodes) {
			b.append("\n\t");
			el.dump(b);
		}				
	}
	
	
}
