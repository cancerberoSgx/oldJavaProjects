package org.sgx.j2s.widget.base;

/**
 * a magnitude is a simple or complex type wich value semantics depends on a "unit" like cm, px, etc
 * we define FloatMag and IntegerMag because java classes doesnt have support for units
 * 
 * in context where unit is not relevant the value can be null;
 * 
 * @author sgurin
 *
 */
public class Magnitude {
//TODO: define all constant (valid)css magnitudes names
	public static final String PX_UNIT ="px";
	public static final String PERCENT_UNIT ="%";
	
	public static final String defaultUnit=PX_UNIT;
	
	String unit;
	
	public Magnitude(String unit) {
		super();
		if(unit!=null)
			this.unit = unit;
	}

	public Magnitude() {
		unit=defaultUnit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	//TODO: conversion between diferent magnitude types
//	public abstrac
}
