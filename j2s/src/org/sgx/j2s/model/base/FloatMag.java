package org.sgx.j2s.model.base;

public class FloatMag extends Magnitude {
	float value;
	
	public FloatMag(float value) {
		this(Magnitude.defaultUnit, value);		
		this.value = value;
	}
	public FloatMag(double value) {
		this(Magnitude.defaultUnit, (float)value);		
		this.value = (float)value;
	}
	public FloatMag(String unit, float value) {
		super(unit);
		this.value = value;
	}
	
	@Override
	public FloatMag clone() {
		return new FloatMag(unit, value);
	}
	
	@Override
	public String toString() {
		return  value+unit;
	}
	
}
