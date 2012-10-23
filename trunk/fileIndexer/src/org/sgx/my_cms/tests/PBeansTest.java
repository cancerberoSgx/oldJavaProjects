//package org.sgx.my_cms.tests;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//import net.sourceforge.pbeans.annotations.*;
//import net.sourceforge.pbeans.data.*; 
//import net.sourceforge.pbeans.*; 
///**
// * soy un bean persistente
// * @author nati
// * 
// */
//@PersistentClass(
//		table="PBeansTest", // Use this one instead of an automatically generated name
//		idField="InternalID", // Use this field name for implicit primary key
//		userManaged=false, // Let pBeans manage schema evolution
//		deleteFields=true
//)
//public class PBeansTest {
//
//	List<PBeansTest> childrens=new LinkedList<PBeansTest>();
//	String attr1;
//	long attr2;
//	Date attr3;
//	int id;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public PBeansTest(List<PBeansTest> childrens, String attr1, long attr2,
//			Date attr3) {
//		super();
//		this.childrens = childrens;
//		this.attr1 = attr1;
//		this.attr2 = attr2;
//		this.attr3 = attr3;
//	}
//	@TransientProperty
//	public List<PBeansTest> getChildrens() {
//		return childrens;
//	}
//	@TransientProperty
//	public void setChildrens(List<PBeansTest> childrens) {
//		this.childrens = childrens;
//	}
//
//	public PBeansTest() {
//		super();
//	}
//	public String getAttr1() {
//		return attr1;
//	}
//
//	public void setAttr1(String attr1) {
//		this.attr1 = attr1;
//	}
//
//	public long getAttr2() {
//		return attr2;
//	}
//
//	public void setAttr2(long attr2) {
//		this.attr2 = attr2;
//	}
//
//	public Date getAttr3() {
//		return attr3;
//	}
//
//	public void setAttr3(Date attr3) {
//		this.attr3 = attr3;
//	}
//
//	
//	
//	
//	public static void main(String[] args) {
//		try {
//			test1();
//		} catch (StoreException e) {
//			e.printStackTrace();
//		}
//	      
//	}
//
//	private static void test1() throws StoreException {
//		GenericDataSource dataSource = new GenericDataSource(); 
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver"); 
//		dataSource.setUrl("jdbc:mysql://localhost/fileindexer?user=root&password=root");
//		
//		Store store = new Store(dataSource);
//		
//		PBeansTest bean1 = new PBeansTest(new LinkedList<PBeansTest>(), "hola", 12345, new Date());
//		store.insert(bean1);
//		
//		
//		  ResultsIterator ri = store.select(PBeansTest.class, "SELECT * FROM PBeansTest", new Object[0]);
//	      try {
//	          while(ri.hasNext()) {
//	        	  PBeansTest b = (PBeansTest) ri.next();
//	              System.out.println("PBeansTest: " + b.getAttr1());
//	          }
//	      } finally {
//	          ri.close();
//	      }
//	      
//	      ri = store.select(PBeansTest.class, "SELECT * FROM PBeansTest where InternalID=4140043957764071965", new Object[0]);
//	      try {
//	          while(ri.hasNext()) {
//	        	  PBeansTest b = (PBeansTest) ri.next();
//	              System.out.println("InternalID=4140043957764071965 name: " + b.getAttr1());
//	          }
//	      } finally {
//	          ri.close();
//	      }
//	}
//
//}
