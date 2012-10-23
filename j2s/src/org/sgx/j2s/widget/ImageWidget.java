package org.sgx.j2s.widget;
/**
 * a image widget contents is a pixel img readed from a resource ided with an url. 
 * the image widget impl must adjust the img to current  widget bounds
 *  
 * @author sgurin
 *
 */
public interface ImageWidget extends Widget {
	
	String getImageSource();
	void setImageSource(String url);
	
}
