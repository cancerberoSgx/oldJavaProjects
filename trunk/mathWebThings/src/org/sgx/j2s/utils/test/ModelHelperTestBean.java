package org.sgx.j2s.utils.test;

public class ModelHelperTestBean {
int i;char c; String s; double d; float f;
boolean b; Integer II; 
public int getI() {
	return i;
}
public void setI(int i) {
	this.i = i;
}
public char getC() {
	return c;
}
public ModelHelperTestBean() {
	this(1,'c',"sss",1.1,1.1f,true,1);
}
public ModelHelperTestBean(int i, char c, String s, double d, float f,
		boolean b, Integer ii) {
	super();
	this.i = i;
	this.c = c;
	this.s = s;
	this.d = d;
	this.f = f;
	this.b = b;
	II = ii;
}
public void setC(char c) {
	this.c = c;
}
public String getS() {
	return s;
}
public void setS(String s) {
	this.s = s;
}
public double getD() {
	return d;
}
public void setD(double d) {
	this.d = d;
}
public float getF() {
	return f;
}
public void setF(float f) {
	this.f = f;
}
public boolean isB() {
	return b;
}
public void setB(boolean b) {
	this.b = b;
}
public Integer getII() {
	return II;
}
public void setII(Integer ii) {
	II = ii;
}



public void setStringFake(String s){}
public Boolean getStringFake(){return true;}
}
