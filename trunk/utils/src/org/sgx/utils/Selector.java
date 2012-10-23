package org.sgx.utils;

public interface Selector<T> {
	boolean select(T t);
}