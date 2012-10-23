package org.sgx.my_cms.impl1.pbeans_;
//package org.sgx.my_cms.impl1;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import net.sourceforge.pbeans.Store;
//import net.sourceforge.pbeans.StoreException;
//import net.sourceforge.pbeans.annotations.*;
//
//import org.apache.lucene.index.CorruptIndexException;
//import org.apache.lucene.queryParser.ParseException;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.store.Directory;
//import org.sgx.my_cms.model.Media;
//import org.sgx.my_cms.model.Repository;
///**
// * this is a persistible bean (pbean)
// * @author sgurin
// *
// */
//@PersistentClass(
//		table="Repository", // Use this one instead of an automatically generated name
//		idField="InternalID", // Use this field name for implicit primary key
//		userManaged=false, // Let pBeans manage schema evolution
//		deleteFields=true, // If a field becomes unused, remove it.
//		indexes={
//				//Unique index for field userID
//				@PropertyIndex(unique=true,propertyNames={"repositoryId"})
//		}
//)
//public class Repository1_pbeans implements Repository {
//	
//	Store store;
////	List<Media> media;
//	Directory dir;
//	String name;
//	public long getRepositoryId() {
//		return repositoryId;
//	}
//
//	public void setRepositoryId(long repositoryId) {
//		this.repositoryId = repositoryId;
//	}
//
//	long repositoryId;
////	FileSearcher1 searcher=new FileSearcher1();
////	FileIndexer1 indexer = new FileIndexer1();
//
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Repository1_pbeans(Directory dir, String name) {
//		super();
//		this.dir = dir;
//		this.name = name;
////		searcher=new FileSearcher1();
////		indexer = new FileIndexer1();
//		try {
//			store =  DBManager.getInstance().getStore();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@TransientProperty
//	public void addMedia(Media m) throws Exception {
//		m.setOwner(this);
//		store.insert(m);
//	}
//	public List<Media> getMedias() {
//		store.select(Media1, "select * from , parameters)
//		return media;
//	}
//	
//
//	public Directory getLuceneDirectory() {
//		return dir;
//	}
//
//	public String getName() {
//		return null;
//	}
//
//	
//	public ScoreDoc[] search(String queryStr)	throws ParseException, CorruptIndexException, IOException{
//		return FileSearcher1.getInstance().search(queryStr, this);
//	}
//	
//	public void index(File file, Media m) throws CorruptIndexException, IOException {
//		FileIndexer1.getInstance().index(file, m);
//	}
//	
//	public void indexDirRec(File dir, Media m, boolean rec) throws CorruptIndexException, IOException {
//		FileIndexer1.getInstance().indexDirRec(dir, m, rec);
//	}
//	
//public boolean equals(Repository1_pbeans r){
//	
//}
//
//@Override
//public List<Media> listMedias() {
//	// TODO Auto-generated method stub
//	return null;
//}
//}
