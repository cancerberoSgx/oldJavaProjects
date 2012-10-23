package org.sgx.j2s.util.impl.test;

public class Bean1 {
String id;
double number;
public Bean1(){}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public double getNumber() {
	return number;
}
public void setNumber(double number) {
	this.number = number;
}
public Bean1(String id, double number) {
	super();
	this.id = id;
	this.number = number;
}
public boolean equals(Object o) {
	return (o instanceof Bean1) && number==((Bean1)o).getNumber() && id.equals(((Bean1)o).getId());
}
}
