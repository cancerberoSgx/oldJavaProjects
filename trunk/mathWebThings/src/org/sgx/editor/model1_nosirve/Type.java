package org.sgx.editor.model1_nosirve;
/**
 * i'm a singleton object that define the concept of editor type and 
 * contains a simple general type collection independent of java.lang.
 * 
 * @author sgurin
 *
 */
public class Type extends EditorProperty{
	public Type(String name, String description, int dimension) {
		super(name, description, null);
		this.dimension = dimension;
	}

	/**
	 * use 0 for a single object, 1 for an array, 2 for a matrix, etc. 0 is default
	 */
	int dimension=0;
public Type(String name, String description) {
		super(name, description);
	}

public static final Type INTEGER = new Type("Integer", 
		"An integer value like 12, -30, etc"), 
		REAL=new Type("Real", "a real value like 1.2, -3.14"),
		BOOLEAN=new Type("Boolean", "true or false"), 
		STRING= new Type("String", "a text");
public int getDimension() {
	return dimension;
}

public void setDimension(int dimension) {
	this.dimension = dimension;
}
}
