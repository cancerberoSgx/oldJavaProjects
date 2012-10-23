package org.sgx.j2s.utils.serialization;
public interface Deserializator {
	Object deserialize(String s) throws ParseException;
}