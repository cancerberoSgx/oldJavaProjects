package org.sgx.swt.editor.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.sgx.editor.Editor;
import org.sgx.editor.EditorListener;
import org.sgx.swt.editor.AbstractEditor;

public class BooleanEditor extends AbstractEditor<Boolean>{

	private Button check;
	
	public BooleanEditor(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
		this.setLayout(new GridLayout());
		check = new Button(this, SWT.CHECK);
	}

	public Boolean getValue() {
		return check.getSelection();
	}

	public void setValue(Object value) {
		if(!(value instanceof Boolean)) 
			throw new RuntimeException("Boolean value required for this editor and "+value+" was given");
		check.setSelection((Boolean)value);
	}
	
}
