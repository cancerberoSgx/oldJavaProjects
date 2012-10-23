package org.sgx.my_cms.model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.LockObtainFailedException;

public interface FileIndexer {

	public static final String NAME_FIELD = "name";
	public static final String PATH_FIELD = "path";
	public static final String EXTENSION_FIELD = "extension";
	public static final String LAST_MODIFICATION_DATE_FIELD = "extension";
	/** boolean value*/
	public static final String IS_DIRECTORY_FIELD = "is directory";
	/** in bytes */
	public static final String SIZE_FIELD = "size";
		
	public static final Store DEFAULT_STORE=Field.Store.YES;
	public static final Index DEFAULT_INDEX = Field.Index.NO;	
	public static final SimpleDateFormat FIELD_DATEFORMAT = new SimpleDateFormat("aaaa-mm-dd");
	
//	public abstract IndexWriter openIndex(String path)
//			throws CorruptIndexException, LockObtainFailedException,
//			IOException;

//	public abstract IndexWriter createIndexWriter(String path)
//			throws CorruptIndexException, LockObtainFailedException,
//			IOException;
//
//	public abstract void index(File file, Media m, IndexWriter writer)
//			throws CorruptIndexException, IOException;
//
//	public abstract Document getDocumentFor(File file, Media m);
//
//	public abstract void close(IndexWriter writer);

}