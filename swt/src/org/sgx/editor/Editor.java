package org.sgx.editor;

/**
 * I represent a user interface "form" that the user modify/fill. Conceptually 
 * the editor try to be the "view" of an object of certain type. 
 * represent an object value of class T (model)
 * my responsabilities are: 1) to know the current value of "represented object"
 * 2) be able to "visually represent " the state of a given model object 
 * 
 * 
 * @author sgx
 *
 */
public interface Editor<T>{
	
	/**
	 * @return if null that mean that the value is not available. use getLastError() . for implementors is usually having a string attribute and assig an alert if the user input does not validate (i.e parsing a number)
	 */
	public T getValue();
/**
 * @see getCurrentValue()
 */
	public String getLastError();
	
	public abstract void setValue(T value);
	

	public void addEditorListener(EditorListener l);

	public void removeEditorListener(EditorListener l);
}
