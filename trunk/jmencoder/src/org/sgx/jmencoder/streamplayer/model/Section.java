package org.sgx.jmencoder.streamplayer.model;

import java.util.List;

public class Section extends StreamElement {
	List<StreamElement> children;

	public Section(String name, String description, String image,
			List<StreamElement> children) {
		super(name, description, image);
		this.children = children;
	}

	public List<StreamElement> getChildren() {
		return children;
	}

	public void setChildren(List<StreamElement> children) {
		this.children = children;
	}

	@Override
	public void dump(StringBuffer b) {
		b.append("Section("+name+" childcount:"+children.size());
	}

}
