package org.sgx.my_cms.impl1;

import org.sgx.my_cms.model.Concept;

public class AbstractConcept implements Concept{

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	String name;
	long id;
}
