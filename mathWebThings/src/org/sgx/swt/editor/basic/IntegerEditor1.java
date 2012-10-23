package org.sgx.swt.editor.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.sgx.editor.Editor;
import org.sgx.editor.EditorListener;
import org.sgx.swt.editor.AbstractEditor;

public class IntegerEditor1 extends AbstractEditor<Integer> {

	Text text = null;	
	
	public IntegerEditor1(Composite parent, int style) {
		super(parent, style);
		_init();
	}

	private void _init() {
		setLayout(new FillLayout());
		text=new Text(this, SWT.BORDER);
	}

	
	public Integer getValue() {
		String s = text.getText();
		int i=0;
		try {
			i=Integer.parseInt(s);
		}catch (Exception e) {
			setLastError("Not an Integer value");
			return null;
		}
		return new Integer(i);
	}

	public void setValue(Object value) {
		text.setText(value.toString());
		
	}
}
