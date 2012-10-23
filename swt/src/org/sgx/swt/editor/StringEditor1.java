package org.sgx.swt.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.sgx.editor.Editor;
import org.sgx.editor.EditorCreator;
import org.sgx.editor.EditorListener;

public class StringEditor1 extends Composite implements Editor<String>{

	static String typeName="java.lang.String";
	static boolean _registered = false;
	Text text = null; 
	@SuppressWarnings("unchecked")
	public StringEditor1(Composite parent, int style, String value) {
		super(parent, style);
		if(!_registered) {
			_registered=true;
			SWTEditorFactory.getInstance().registerEditor(typeName, 
				new EditorCreator<String>() {
					public Editor<String> create(Composite parent, int style) {
						return new StringEditor1(parent, style, "");
					}
				}); 
		}

		_init(value);
	}

	private void _init(String value) {
		setLayout(new FillLayout());
		text=new Text(this, SWT.NONE);
		setValue(value); 
	}


	public String getValue() {
		return text.getText();
	}
	public void setValue(String value) {
		text.setText(value);
	}
	
	
	//hey use a native widget if you need keytype events or something. this is a very simple editor that only supports get/set
	public void removeEditorListener(EditorListener l) {
		// TODO Auto-generated method stub		
	}
	public void addEditorListener(EditorListener l) {
		// TODO Auto-generated method stub		
	}

	public String getLastError() {
		// TODO Auto-generated method stub
		return null;
	}

}
