package org.sgx.my_cms.impl1;

import java.io.File;
import java.io.InputStream;

import org.sgx.my_cms.model.Document;
import org.sgx.my_cms.model.Media;
import org.sgx.my_cms.model.Repository;


/**
 * una implementación genérica de medio en filesystem. Este medium se "monta" en un dir 
 * del filesystem existente. dicho dir (rootPath) se representa con un string utilizando el 
 * formato del so anfitrión para absolute paths.
 * @author nati
 *
 */
public class Media1 extends AbstractConcept implements Media {
	String rootPath;
	private Repository owner;

	public Media1(String name, String rootPath) {
		super();
		this.rootPath = rootPath;
	}

	public String getAddressOf(Document d) {
		return rootPath+File.separator+d.getFullPath();
	}

	public String getRootPath() {
		return rootPath;
	}

	public Repository getOwner() {
		return owner;
	}

	public String getRelativePathOf(File file) {
		return file.getAbsolutePath().substring(getRootPath().length(),file.getAbsolutePath().length() );
	}

	public void setOwner(Repository r) {
		this.owner=r;
	}

}
