package org.sgx.swt.editor;

import org.eclipse.swt.widgets.Composite;
import org.sgx.editor.Editor;
import org.sgx.editor.EditorListener;

public abstract class AbstractEditor<T> extends Composite implements Editor<T>{

	
	private String lastError;

	public AbstractEditor(Composite parent, int style) {
		super(parent, style);
	}


	public abstract T getValue();
	public abstract void setValue(Object value);
	
	
	//hey use a native widget if you need keytype events or something. this is a very simple editor that only supports get/set
	public void removeEditorListener(EditorListener l) {
		// TODO Auto-generated method stub		
	}
	public void addEditorListener(EditorListener l) {
		// TODO Auto-generated method stub		
	}

	public String getLastError() {
		return lastError;
	}
	public void setLastError(String er) {
		lastError=er;
	}

}