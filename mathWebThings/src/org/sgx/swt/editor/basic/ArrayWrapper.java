package org.sgx.swt.editor.basic;
/**
 * use this class for representing 1 or 2 dimension arrays for your editor
 * the objective is to easy array modeling in java and java2script (dimension, length, guessing).
 * the thricky part for supporting n dimensions is that because java 
 * doesn't support array overloading
 * support up to 2 d arrays
 * 
 * you construct an arraywrapper with array type value. you can ask arraywrapper for its dimensions
 * @author nati
 *
 */
public class ArrayWrapper {
	int [] dimLength = new int[5]; 
	/**
	 * if dimension == 1 (array) getLength(0) will return the array length
	 * if(dimension==2) (matrix) getLength(0) will return the number of columns and getLength(1) will return the number of rows
	 */
	public int getlength(int dim) {
		return dimLength[dim];
	}
//	public ArrayWrapper(Object value) {
//		this.dimension = 0;
//		this.value = value;
//
//		init();
//	}
//	public ArrayWrapper(Object[][]value) {
//		System.out.println("cons 2");
//		this.dimension = 2;
//		this.value = value;
//		init();
//	}
//	public ArrayWrapper(Object[] value) {
//		System.out.println("cons 1");
//		this.dimension = 1;
//		this.value = value;
//		init();
//	}
	
	public ArrayWrapper(Object[] value,int dimension) {
		this.value = value;
		this.dimension=dimension;
		init();
	}
	/**
	 * use 0 for a single object, 1 for an array, 2 for a matrix, etc. 0 is default
	 */
	int dimension=0;
	Object value;
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	private void init() {
		if(value==null)
			throw new RuntimeException("null is not a valid array value");
		for (int i = 0; i < dimLength.length; i++) {
			dimLength[i]=-1;
		}
		if(dimension==2) {
			Object[][] a = (Object[][])value;
			if(a.length==0) {
				throw new RuntimeException("empty array [][] is not a valid array value");
			}
			dimLength[0]=a.length;
			dimLength[1]=a[0].length;
		}
		else if(dimension==1) {
			Object[] a = (Object[])value;
			if(a.length==0) {
				throw new RuntimeException("empty array [] is not a valid array value");
			}
			dimLength[0]=a.length;
		}
//		else if (dimension==0) {
////			dimLength[0]=0;
//		}
		else
			throw new RuntimeException("not a valid dimension");
	}

	void checkDimensionAndIndex(int i) {
		if(dimension!=1) 
			throw new RuntimeException("invalid array dimensions. must be 1d and is "+dimension);
		if(!(i<dimLength[0]))
			throw new RuntimeException("index out of range: "+i+" and array is "+dimLength[0]);
	}
	void checkDimensionAndIndex(int i, int j) {
		if(dimension!=2) {
			throw new RuntimeException("invalid array dimensions. must be 2d and is "+dimension);
		} 
		if(!(i<dimLength[0] && j<dimLength[1]))
			throw new RuntimeException("index out of range: "+i+", "+j+" and array is "+dimLength[0]+","+dimLength[1]);
	}
	public  Object getItem(int i){
		checkDimensionAndIndex(i);
		Object[] a = (Object[])value;
		return a[i];
	}
	public  Object getItem(int i, int j){
		if(dimension==1 && j==0)
			return getItem(i);
		checkDimensionAndIndex(i,j);		
		Object[][] a = (Object[][])value;
		return a[i][j];
		
	}
	public void setItem(int i, Object o) {
		checkDimensionAndIndex(i);
		Object[] a = (Object[])value;
		a[i]=o;
	}
	public void setItem(int i, int j, Object o) {
		checkDimensionAndIndex(i,j);
		Object[][] a = (Object[][])value;
		a[i][j]=o;
	}
	

}
