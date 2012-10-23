package org.sgx.my_cms.model;

public interface Document {
	
	String getFullPath();
	String getName();
	String getExtension();
	long getSize();
	Document[] getChildren();
	void addChildren(Document c);
	void removeChildren();
}
