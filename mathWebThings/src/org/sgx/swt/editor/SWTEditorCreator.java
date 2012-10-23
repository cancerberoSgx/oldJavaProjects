package org.sgx.swt.editor;

import org.eclipse.swt.widgets.Composite;
import org.sgx.editor.Editor;
//import org.sgx.editor.EditorCreator;


public interface SWTEditorCreator<T>/* implements EditorCreator<T>*/{

//	public SWTEditorCreator(Composite parent, int style) {
//		super();
//		this.parent = parent;
//		this.style = style;
//	}
//	public SWTEditorCreator() {
//		// TODO Auto-generated constructor stub
//	}
//	Composite parent;
//	int style;
	
	
	 Editor<T> create(Composite parent, int style);

//	public Composite getParent() {
//		return parent;
//	}
//
//
//	public void setParent(Composite parent) {
//		this.parent = parent;
//	}
//
//
//	public int getStyle() {
//		return style;
//	}
//
//
//	public void setStyle(int style) {
//		this.style = style;
//	}
	

}
//