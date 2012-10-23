package org.sgx.my_cms.impl1;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.sgx.my_cms.impl1.lucene.FileIndexer1;
import org.sgx.my_cms.impl1.lucene.FileSearcher1;
import org.sgx.my_cms.model.Media;
import org.sgx.my_cms.model.Repository;

public class Repository1 extends AbstractConcept implements Repository {
	
	Set<Media> medias;
	String luceneDirPath;
	
//	public long getId() {
//		return id;
//	}

	public long getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(long repositoryId) {
		this.repositoryId = repositoryId;
	}

	long repositoryId;
//	public void setName(String name) {
//		this.name = name;
//	}

	public Repository1(String luceneDirPath, String name) {
		super();
		this.luceneDirPath = luceneDirPath;
		this.name = name;
	}
	
	

	public String getLuceneDirectory() {
		return luceneDirPath;
	}

//	public String getName() {
//		return null;
//	}

	
	public ScoreDoc[] search(String queryStr)	throws ParseException, CorruptIndexException, IOException{
		return FileSearcher1.getInstance().search(queryStr, this);
	}
	
	public void index(File file, Media m) throws CorruptIndexException, IOException {
		FileIndexer1.getInstance().index(file, m);
	}
	
	public void indexDirRec(File dir, Media m, boolean rec) throws CorruptIndexException, IOException {
		FileIndexer1.getInstance().indexDirRec(dir, m, rec);
	}
	
public void removeMedia(Media m) throws Exception {
	
}
public List<Media> getMedias() {
	// TODO Auto-generated method stub
	return null;
}

public void addMedia(Media m) throws Exception {
	m.setOwner(this);
	medias.add(m);
}
}
