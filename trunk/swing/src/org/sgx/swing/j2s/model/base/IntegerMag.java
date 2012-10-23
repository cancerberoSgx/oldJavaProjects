package org.sgx.swing.j2s.model.base;
/**
 * 
 * @author sgurin
 *
 */
public class IntegerMag extends Magnitude {
	int value;
	
	public IntegerMag(int value) {
		this(Magnitude.defaultUnit, value);		
		this.value = value;
	}
	public IntegerMag(String unit, int value) {
		super(unit);
		this.value = value;
	}
	
	@Override
	public IntegerMag clone() {
		return new IntegerMag(unit, value);
	}
	
	@Override
	public String toString() {
		return  value+unit;
	}
	
}
