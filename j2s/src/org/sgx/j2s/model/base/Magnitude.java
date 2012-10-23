package org.sgx.j2s.model.base;

/**
 * a magnitude is a simple or complex type wich value semantics depends on a "unit" like cm, px, etc
 * we define FloatMag and IntegerMag because java classes doesnt have support for units
 * 
 * @author sgurin
 *
 */
public class Magnitude {
//TODO: define all constant (valid)css magnitudes names
	public static final String PX_UNIT ="px";
	public static final String CM_UNIT ="cm";
	
	public static final String defaultUnit=PX_UNIT;
	
	String unit;
	
	public Magnitude(String unit) {
		super();
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
