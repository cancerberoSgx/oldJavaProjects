package org.sgx.utils.zip;
/**
 * 
 * @author sgurin
 *
 */
public interface ZipListener {
/**
 * 
 * @param abspath file ziped abs path
 * @param numOfFiles total file count being zipped
 * @param size total size being zipped
 */
	void  notifyFileZip(String abspath, long numOfFiles, long size);
}
