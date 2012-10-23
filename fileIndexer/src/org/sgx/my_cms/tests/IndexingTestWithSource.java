package org.sgx.my_cms.tests;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.sgx.my_cms.impl1.Media1;
import org.sgx.my_cms.impl1.Repository1;
import org.sgx.my_cms.impl1.lucene.FileIndexer1;
import org.sgx.my_cms.model.FileIndexer;
import org.sgx.my_cms.model.Media;
import org.sgx.my_cms.model.Repository;
import org.sgx.my_cms.utils.LuceneUtils;

public class IndexingTestWithSource {

private static final String FUENTES_DIR = "C:\\dev\\workspace\\fileIndexer";
private static final String LUCENE_INDEX_PATH = "index.lucene.probe";

//	static String Source
	public static void main(String[] args) {
		try {
			testWithSources();
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

private static void testWithSources() throws CorruptIndexException, IOException {
	File f = null;
	boolean create;
	if ((f = new File(LUCENE_INDEX_PATH)).exists() && f.isDirectory()) 
		create = false;
	else 
		create = true;
	IndexWriter writer = new IndexWriter(LUCENE_INDEX_PATH, new StandardAnalyzer(), create);
	writer.setMergeFactor( 20);
	writer.close();
//	writer = new IndexWriter(LUCENE_INDEX_PATH, new StandardAnalyzer(), create);
//	writer.setMergeFactor( 20);
//	Repository1 rep = new Repository1(writer.getDirectory(), "mis fuentes");	
//	Media m1 = new Media1("mediaprobe", "/");
//	m1.setOwner(rep);
//	rep.indexDirRec(new File(FUENTES_DIR), m1, true);
}

}
