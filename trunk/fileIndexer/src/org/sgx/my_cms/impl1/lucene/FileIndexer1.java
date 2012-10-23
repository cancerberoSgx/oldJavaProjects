package org.sgx.my_cms.impl1.lucene;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.ParallelReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.sgx.my_cms.impl1.Repository1;
import org.sgx.my_cms.model.FileIndexer;
import org.sgx.my_cms.model.Media;
import org.sgx.my_cms.utils.FSUtils;
import org.sgx.my_cms.utils.LuceneUtils;
/**
 * @author sgurin
 *
 */
public class FileIndexer1 implements FileIndexer {
	private static FileIndexer1 instance;
	
	

	private FileIndexer1() {
	}

	public static FileIndexer1 getInstance() {
		if (null == instance) {
			instance = new FileIndexer1();
		}
		return instance;
	}
	public void indexDirRec(File dir, Media m, boolean rec) throws CorruptIndexException, IOException {
		indexDirRec_(dir,m,rec);
//		((Repository1) m.getOwner()).getLuceneDirectory().close();
	}
	private void indexDirRec_(File dir, Media m, boolean rec) throws CorruptIndexException, IOException {
		if(dir.isDirectory() && rec){
			File[] childs = dir.listFiles();
			for (int i = 0; i < childs.length; i++) {
				indexDirRec(childs[i], m, rec);
			}
		}
		index(dir, m);
	}
	/* (non-Javadoc)
	 * @see indexer.lucene.FileIndexer#index(java.io.File, org.sgx.indexer.model.Media, org.apache.lucene.index.IndexWriter)
	 */
	public void index( File file, Media m) throws CorruptIndexException, IOException {
		IndexWriter writer = LuceneUtils.openIndexWriter(((Repository1) m.getOwner()).getLuceneDirectory());
		writer.addDocument(getDocumentFor(file, m));
		writer.optimize();
	}	
	/* (non-Javadoc)
	 * @see indexer.lucene.FileIndexer#getDocumentFor(java.io.File, org.sgx.indexer.model.Media)
	 */
	public Document getDocumentFor(File file, Media m) {
		Document doc = new Document();
		doc.add(new Field(NAME_FIELD, file.getName(), DEFAULT_STORE, DEFAULT_INDEX));
		doc.add(new Field(NAME_FIELD, file.getName(), DEFAULT_STORE, DEFAULT_INDEX));
		doc.add(new Field(PATH_FIELD, m.getRelativePathOf(file), DEFAULT_STORE, DEFAULT_INDEX));
		doc.add(new Field(LAST_MODIFICATION_DATE_FIELD, FIELD_DATEFORMAT.format(new Date(file.lastModified())), DEFAULT_STORE, DEFAULT_INDEX));
		doc.add(new Field(SIZE_FIELD, file.length()+"", DEFAULT_STORE, DEFAULT_INDEX));
		doc.add(new Field(EXTENSION_FIELD, FSUtils.getFileExtension(file), DEFAULT_STORE, DEFAULT_INDEX));
		return doc;
	}
}
