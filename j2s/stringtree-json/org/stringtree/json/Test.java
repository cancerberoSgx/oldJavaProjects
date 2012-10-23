package org.stringtree.json;

import java.util.Map;


/**
 * i'm a java bean and a test case also
 * @author SGURIN
 *
 */
public class Test {

	String name;
	int age;
	boolean man;
	Object[] arr;
	Test test;
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMan() {
		return man;
	}

	public void setMan(boolean man) {
		this.man = man;
	}

	public Object[] getArr() {
		return arr;
	}

	public void setArr(Object[] arr) {
		this.arr = arr;
	}

	public Test(String name, int age, boolean man, Object[] arr) {
		super();
		this.name = name;
		this.age = age;
		this.man = man;
		this.arr = arr;
	}

	public boolean equals(Test t) {
		return t.getName().equals(getName()) && 
			t.getAge()==getAge() && t.isMan()==isMan() && arraysEquals(t.getArr(), getArr());
		
	}
	public static  boolean arraysEquals(Object[] a, Object[] b) {
		if(a!=null && b!=null) {
			if(a.length==b.length) {
				for (int i = 0; i < a.length; i++) {
					if(!a[i].equals(b[i]))
						return false;
				}
				return true;
			}
			else
				return false;
		}
		else if(a==null  && b==null)
			return true;
		else 
			return false;
	}

	public String toString() {
		return "name: "+name+" man: "+man+" age: "+age+" arr: "+
			arrayToString(arr)+" test: "+(test!=null?test.toString():"null");
	}
	public static String arrayToString(Object[] a) {
		String s="[";
		for(int i=0;i<a.length;i++) {
			if(i!=a.length-1)
				s=s+a[i]+",";	
		}
		return s+"]";
	}
	
	public static void main(String[] args) {	
		Test t = new Test("seba", 18, true, new String[]{"hello", "world"});
		t.setTest(new Test("laura", 12, false, new String[]{"hi", "tini"}));
		JSONWriter writer = new JSONWriter(false);
		String json = writer.write(t);
		System.out.println(json);
		JSONReader reader = new JSONReader();
		Map m =(Map) reader.read(json);
		
		String name = (String)m.get("name");
		Long age =  (Long)m.get("age");
		boolean man = (Boolean)m.get("man");
		java.util.ArrayList arr = (java.util.ArrayList) m.get("arr");
		Test test = (Test) m.get("test"); 
		
		System.out.println("name: "+name+" age: "+age+" man: "+man);
				
		Test t2 = new Test(name, age.intValue(), man, arr.toArray());
		t2.setTest(test);
		if(t.equals(t2))
			System.out.println("original and parsed ARE equals");
		else
			System.out.println("original and parsed are NOT equals");
		
		if(t.toString().equals(t2.toString()))
			System.out.println("toString () original and parsed ARE equals");
		else
			System.out.println("toString ()  original and parsed are NOT equals");
		
		System.out.println(t);
		System.out.println(t2);
				
	}

}
