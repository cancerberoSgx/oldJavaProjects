package org.sgx.j2s.utils;

public abstract class AbstractRunnable<T> implements Runnable{
T data;

public AbstractRunnable(T data) {
	super();
	this.data = data;
}

public T getData() {
	return data;
}

public void setData(T data) {
	this.data = data;
}

}
