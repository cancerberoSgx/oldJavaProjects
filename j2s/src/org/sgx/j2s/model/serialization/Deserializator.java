package org.sgx.j2s.model.serialization;
public interface Deserializator {
	Object deserialize(String s) throws ParseException;
}