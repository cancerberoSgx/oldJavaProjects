package org.sgx.my_cms.model;

import java.util.List;

public interface Repository extends Concept {


 void addMedia(Media m) throws Exception;
 void removeMedia(Media m) throws Exception;
 List<Media> getMedias();
 
// Directory getLuceneDirectory();
// ScoreDoc[] search(String queryStr)	throws ParseException, CorruptIndexException, IOException;
}
