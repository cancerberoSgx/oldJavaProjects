package org.sgx.pickturemakeup.model;

public class NumericProperty extends Property {
	Object max, min;
	
	public NumericProperty(String id, Object value, 
			String description, Object max, Object min) {
		super(id, description, value);
		this.max = max;
		this.min = min;
	}
	public NumericProperty(String id, Object value, Object max, Object min) {
		super(id, value);
		this.max = max;
		this.min = min;
	}
	
	public Object getMax() {
		return max;
	}
	
	public void setMax(Object max) {
		this.max = max;
	}
	
	public Object getMin() {
		return min;
	}
	
	public void setMin(Object min) {
		this.min = min;
	}
}
