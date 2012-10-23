package org.sgx.j2s.util.impl.test;

public class ObjectUtilsTestBean2 {
	String foo ; 
	int integer;
	double real;
	boolean bool1;
	public ObjectUtilsTestBean2(){}
	public ObjectUtilsTestBean2(String foo, int integer, double real, boolean bool1) {
		super();
		this.foo = foo;
		this.integer = integer;
		this.real = real;
		this.bool1 = bool1;
	}
	public String getFoo() {
		return foo;
	}
	public void setFoo(String foo) {
		this.foo = foo;
	}
	public int getInteger() {
		return integer;
	}
	public void setInteger(int integer) {
		this.integer = integer;
	}
	public double getReal() {
		return real;
	}
	public void setReal(double real) {
		this.real = real;
	}
	public boolean isBool1() {
		return bool1;
	}
	public void setBool1(boolean bool1) {
		this.bool1 = bool1;
	}

}