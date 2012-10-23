package org.sgx.utils.tests;

public class ObjectUtilsTestBean {
String foo ; 
Integer integer;
Double real;
Boolean bool1;
public ObjectUtilsTestBean(){}
public ObjectUtilsTestBean(String foo, Integer integer, Double real, Boolean bool1) {
	super();
	this.foo = foo;
	this.integer = integer;
	this.real = real;
	this.bool1 = bool1;
}
public Boolean getBool1() {
	return bool1;
}
public void setBool1(Boolean bool1) {
	this.bool1 = bool1;
}
public String getFoo() {
	return foo;
}
public void setFoo(String foo) {
	this.foo = foo;
}
public Integer getInteger() {
	return integer;
}
public void setInteger(Integer integer) {
	this.integer = integer;
}
public Double getReal() {
	return real;
}
public void setReal(Double real) {
	this.real = real;
}
}
