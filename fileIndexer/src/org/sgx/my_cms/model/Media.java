package org.sgx.my_cms.model;

import java.io.File;

/**
 * responsabilities:
 * know the phisical address of a document content
 * 
 * @author sgurin
 *
 */
public interface Media extends Concept{

	String getRootPath();
	
	Repository getOwner();
	void setOwner(Repository r);	
	/**
	 * cada implementación entenderá su propio formato de address. 
	 * algunas serán urls, otras filesystem paths, etc 
	 * @param f
	 * @return path that totally identify the Document inside this media.
	 */
	String getAddressOf(Document d);
	String getRelativePathOf(File file);
	
}
