package org.sgx.utils.tests;

public class JavaUtilsTestBean {

	//i'm a java bean
	String prop1;
	Integer prop2;
	
	boolean booleanProp1;
	public JavaUtilsTestBean() {}
	public JavaUtilsTestBean(String prop1, Integer prop2) {
		super();
		this.prop1 = prop1;
		this.prop2 = prop2;
	}
	

	public JavaUtilsTestBean(String prop1, Integer prop2, boolean booleanProp1) {
		this(prop1, prop2);
		this.booleanProp1 = booleanProp1;
	}
	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public Integer getProp2() {
		return prop2;
	}

	public void setProp2(Integer prop2) {
		this.prop2 = prop2;
	}

}
