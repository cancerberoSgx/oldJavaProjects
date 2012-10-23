package org.sgx.swt.editor.generic;

import org.eclipse.swt.widgets.Composite;
import org.sgx.swt.editor.AbstractEditor;
/**
 * this is an object viewer that represent the object with 
 * toString() method label that when clicked "opens an editor". 
 */
public class ObjectLineEditor<T> extends AbstractEditor<T>{

	public ObjectLineEditor(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public T getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
	}

}
