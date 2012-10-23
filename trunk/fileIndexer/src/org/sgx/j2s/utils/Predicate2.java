package org.sgx.j2s.utils;

public interface Predicate2<T, V> {
	/**
	 * @return null is this obj doesn't have to be collected.
	 */
	V select(V obj);
}
