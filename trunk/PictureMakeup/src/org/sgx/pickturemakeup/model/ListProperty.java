package org.sgx.pickturemakeup.model;

import java.util.List;

public class ListProperty extends Property{
	
	boolean multiple=true;
	List<String> list;
	
	public ListProperty(String id, String description, Object value) {
		super(id, description, value);
		if(value instanceof List)
			this.list = (List)value;
		else 
			throw new RuntimeException("ListProperty values must be a List");
	}
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	

}
