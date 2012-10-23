package org.sgx.my_cms.impl1.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocCollector;
import org.sgx.my_cms.impl1.Repository1;
import org.sgx.my_cms.model.FileSearcher;
import org.sgx.my_cms.model.Repository;

public class FileSearcher1 implements FileSearcher {	
	private static FileSearcher1 instance;

	private FileSearcher1() {
	}

	public static FileSearcher1 getInstance() {
		if (null == instance) {
			instance = new FileSearcher1();
		}
		return instance;
	}
	private static final int DEFAULT_HITS_PER_PAGE = 10;

	/* (non-Javadoc)
	 * @see org.sgx.indexer.model.impl1.FileSearcher#search(java.lang.String, org.sgx.indexer.model.Repository)
	 */
	public ScoreDoc[] search(String queryStr, Repository rep) throws ParseException, CorruptIndexException, IOException {
		StandardAnalyzer analyzer = new StandardAnalyzer();
		Query q = new QueryParser(DEFAULT_SEARCH_FIELD, analyzer).parse(queryStr);
		IndexSearcher searcher = new IndexSearcher(((Repository1) rep).getLuceneDirectory());
		TopDocCollector collector = 
		  new TopDocCollector(DEFAULT_HITS_PER_PAGE);
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		return hits;
	}
//	public IndexSearcher createIndexSearcher()
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
