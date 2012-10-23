package org.sgx.swt.editor.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.sgx.editor.Editor;
//import org.sgx.editor.EditorCreator;
import org.sgx.editor.EditorListener;
import org.sgx.swt.editor.AbstractEditor;

public class StringEditor1 extends AbstractEditor<String>{

	Text text = null; 	
	
	public StringEditor1(Composite parent, int style) {
		super(parent, style);		
		_init();
	}

	private void _init() {
		setLayout(new FillLayout());
		text=new Text(this, SWT.BORDER);
	}

	public String getValue() {
		return text.getText();
	}
	
	public void setValue(Object value) {
//		if(!(value instanceof String)) {
//			throw new RuntimeException("this editor requires string required ");
//		}
		text.setText((String) value);
	}

}

