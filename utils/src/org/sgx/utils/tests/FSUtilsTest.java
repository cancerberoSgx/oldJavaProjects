package org.sgx.utils.tests;

import java.io.IOException;

import org.sgx.utils.FSUtils;


public class FSUtilsTest {
String attr1;
public FSUtilsTest(){
	attr1=null;
}
	public FSUtilsTest(String attr1) {
	super();
	this.attr1 = attr1;
}
	public String getAttr1() {
	return attr1;
}
public void setAttr1(String attr1) {
	this.attr1 = attr1;
}
	public static void main(String[] args) {
		try {
			String s = FSUtils.serialize(new FSUtilsTest("seba"));
			System.out.println(s+"\n\n");
			FSUtilsTest o = (FSUtilsTest)FSUtils.deserialize(s);
			System.out.print(o.getAttr1());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
