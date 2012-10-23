package org.sgx.j2s.buggy;

public class OverloadBug {
public static void main(String[]a) {
//	new Base().notifyAll(new Event("1"));
	new Extension().method2();
}
}  

class Base {
	public void method1() {
		notifyAll(new Event("2"));
	}
	public void notifyAll(Event e) {
		System.out.println("Base::notifyAll");
	}
}

class Extension extends Base {
	public void method2() {
		super.method1();
	}
	public void notifyAll(Event e) {
		System.out.println("Super::notifyAll");
	}
}

class Event {
	String id;
	public String getId() {
		return id;
	}
	public Event(String id) {
		super();
		this.id = id;
	}
}