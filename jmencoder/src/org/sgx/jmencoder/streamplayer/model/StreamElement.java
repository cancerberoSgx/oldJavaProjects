package org.sgx.jmencoder.streamplayer.model;

import org.sgx.utils.Utils;

public abstract class StreamElement {

	public static final String PROP_NAME="name", 
		PROP_IMAGE="image", 
		PROP_DESCRIPTION="description";
	
	String image;
	String name;
	String description;

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StreamElement(String name, String description, String image) {
		super();
		this.name = name;
		this.description = description;
		this.image=image;
	}
	
	public void toXMLAttrs(StringBuffer b) {
		b.append(" "+PROP_NAME+"="+Utils.quote(name)+" "+PROP_DESCRIPTION+"="+
			Utils.quote(description)+" "+PROP_IMAGE+"="+Utils.quote(image)+" ");
	}
	public abstract void dump(StringBuffer b);
	
	public String toString(){
		return name;
	}
}
