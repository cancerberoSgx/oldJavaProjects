package org.sgx.webMatrix.logic;

import java.util.HashMap;
import java.util.Map;

import org.sgx.math.Jama.Matrix;

/**
 * I make some matrix operation taking 2 matrix arguments (a and b) and return a 
 * resultName->Matrix|number result map to be interpreted by some viewer
 * 
 * @author sg
 *
 */
public abstract class OperationBuilder {

	Map<String, Object> results = new HashMap<String, Object>();
	public OperationBuilder(){}
	public OperationBuilder(Matrix a, Matrix b) {
		super();
		this.a = a;
		this.b = b;
	}

	Matrix a, b;
	
	public void clearResults() {
		results.clear();
	}
	/**
	 * fill result attribute so getResults() can be called
	 */
	public abstract void  buildResults();

	public Matrix getA() {
		return a;
	}

	public void setA(Matrix a) {
		this.a = a;
	}

	public Matrix getB() {
		return b;
	}

	public void setB(Matrix b) {
		this.b = b;
	}

	public Map<String, Object> getResults() {
		return results;
	}
}
