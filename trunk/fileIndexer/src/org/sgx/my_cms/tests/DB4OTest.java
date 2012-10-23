package org.sgx.my_cms.tests;

import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class DB4OTest {
	String attr1;
	List<DB4OTest> childrens;
	public DB4OTest(){
		attr1="seba";
		childrens=new LinkedList<DB4OTest>();
	}
	public DB4OTest(String attr1, List<DB4OTest> children) {
		this.attr1=attr1;
		this.childrens=children;
	}
	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public List<DB4OTest> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<DB4OTest> childrens) {
		this.childrens = childrens;
	}
	private static final String DB4OFILENAME = "probe_db4o.yap";
	public String toString(){
		return attr1+" - "+childrens.size();
	}


	public static void main(String[] args) {
		test1();
	}
	private static void test1() {
		ObjectContainer db=Db4o.openFile(DB4OFILENAME);
		try {
			DB4OTest o1 = new DB4OTest(), o2 = new DB4OTest();
			o1.getChildrens().add(o2);
			db.store(o1);
		}
		finally {
			db.close();
		}

		db=Db4o.openFile(DB4OFILENAME);
		try {
			DB4OTest proto=new DB4OTest(null,null);
			ObjectSet<DB4OTest> result=db.query(DB4OTest.class);
			System.out.println(result.size());
		    while(result.hasNext()) {
		        System.out.println(result.next());
		    }
		}
		finally {
			db.close();
		}
	}

}
