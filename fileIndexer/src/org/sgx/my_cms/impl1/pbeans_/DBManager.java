//package org.sgx.my_cms.impl1.pbeans_;
//
//import net.sourceforge.pbeans.Store;
//import net.sourceforge.pbeans.StoreException;
//import net.sourceforge.pbeans.data.GenericDataSource;
//
//public class DBManager {	
//	private static DBManager instance;	
//	public static DBManager getInstance() {
//		if (null == instance) {
//			instance = new DBManager();
//		}
//		return instance;
//	}
//
//	private GenericDataSource dataSource;
//	private DBManager(){
//		dataSource = new GenericDataSource(); 
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver"); 
//		dataSource.setUrl("jdbc:mysql://localhost/fileindexer?user=root&password=root");
//	}
//	
//	public Store getStore() throws StoreException{
//		return new Store(dataSource);
//	}
//
//}
