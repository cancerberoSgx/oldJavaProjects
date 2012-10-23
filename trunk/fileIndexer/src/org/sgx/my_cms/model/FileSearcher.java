package org.sgx.my_cms.model;

import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;

public interface FileSearcher {

	public static final String DEFAULT_SEARCH_FIELD = "name";
	
	 ScoreDoc[] search(String queryStr, Repository rep)
			throws ParseException, CorruptIndexException, IOException;

}