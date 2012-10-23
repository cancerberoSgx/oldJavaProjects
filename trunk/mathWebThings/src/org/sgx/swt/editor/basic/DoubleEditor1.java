package org.sgx.swt.editor.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.sgx.editor.Editor;
import org.sgx.editor.EditorListener;
import org.sgx.swt.editor.AbstractEditor;

public class DoubleEditor1 extends AbstractEditor<Double> {

	Text text = null;
	
	
	public DoubleEditor1(Composite parent, int style) {
		super(parent, style);
		_init();
	}

	private void _init() {
		setLayout(new FillLayout());
		text=new Text(this, SWT.BORDER);
	}

	

	public Double getValue() {
		String s = text.getText();
		double i=0;
		try {
			i=Double.parseDouble(s);
		}catch (Exception e) {
			setLastError("Not an floating point value");
			return null;
		}
		return new Double(i);
	}

	public void setValue(Object value) {
		text.setText(value.toString());		
	}
}
