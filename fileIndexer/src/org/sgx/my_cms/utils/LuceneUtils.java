package org.sgx.my_cms.utils;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.ParallelReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

public class LuceneUtils {
	/* (non-Javadoc)
	 * @see indexer.lucene.FileIndexer#openIndex(java.lang.String)
	 */
	public static IndexWriter openIndexWriter(Directory d) throws CorruptIndexException, LockObtainFailedException, IOException {
		return  new IndexWriter(d, new StandardAnalyzer(), false);
	}
	
	public static IndexWriter openIndexWriter(String dirPath) throws CorruptIndexException, LockObtainFailedException, IOException {
		return  new IndexWriter(dirPath, new StandardAnalyzer(), false);
	}
	/* (non-Javadoc)
	 * @see indexer.lucene.FileIndexer#createIndex(java.lang.String)
	 */
	public static IndexWriter createIndexWriter(String path) throws CorruptIndexException, LockObtainFailedException, IOException {
		return  new IndexWriter(path, new StandardAnalyzer(), true);
	}	
	
	/* (non-Javadoc)
	 * @see indexer.lucene.FileIndexer#close(org.apache.lucene.index.IndexWriter)
	 */
	public static void closeIndexWriter(IndexWriter writer) {
		if(null != writer) {
			try {
				writer.close();
			} catch(Exception e) {
			}
		}
	}
	
	public static FSDirectory createFSDirectory(File f) throws IOException {
		return FSDirectory.getDirectory(f, true);
	}
	public static FSDirectory openFSDirectory(File f) throws IOException {
		return FSDirectory.getDirectory(f, false);
	}
}
