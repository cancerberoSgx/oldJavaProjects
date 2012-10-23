package org.sgx.my_cms.utils;

import java.io.File;

public class FSUtils {

	public static String getFileExtension(File f) {
		if(f.isDirectory())
			return "";
		else{			
			String filename = f.getName();
			return filename.substring(filename.lastIndexOf('.')+1, filename.length());
		}
	}

}
