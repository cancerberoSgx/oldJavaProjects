package org.sgx.swt.editor.basic;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.sgx.editor.Editor;
import org.sgx.editor.EditorListener;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.editor.AbstractEditor;
import org.sgx.swt.editor.SWTEditorFactory;

/**
 * simple matrix of string editor
 * matrix is trpresented with: String[row][column]
 * @author sg
 *
 */
public class ArrayEditor extends AbstractEditor<ArrayWrapper>{
	
	private ArrayWrapper gdata;
	private Editor[][] editor;
	private int columns;
	private int rows;

	public ArrayEditor(Composite parent, int style) {
		super(parent, SWT.BORDER);
		this.gdata=new ArrayWrapper(new String[][]{{}}, 2);
		init();
		
	} 

	private void init() {
		if(gdata==null)
			throw new RuntimeException("invalid data: "+gdata);	
		
		rows=gdata.getlength(0);		
		if(gdata.getDimension()>1)
			columns = gdata.getDimension()>1 ? gdata.getlength(1) : 0;
			
		this.editor = new Editor[rows][columns];
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = columns;
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Object o = gdata.getItem(i,j);
				Editor ed = SWTEditorFactory.getInstance().getEditorForObject(o, this, SWT.NONE);
				editor[i][j]=ed;
			}
		}
		setLayout(gridLayout);
	}
	
	public ArrayWrapper getData() {
		Object[][] current = new Object[rows][columns];
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				current[i][j]=editor[i][j].getValue();
			}
		}
		return new ArrayWrapper(current, gdata.getDimension());
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}
	
	public void setValue(Object val) {
		if(!(val instanceof ArrayWrapper))
			throw new RuntimeException("editor requires ArrayWrapper value");
		SWTUtils.compositeDestroyAllChildrens(this);
		this.gdata=(ArrayWrapper) val;
		init();
		this.pack();
	}

	public ArrayWrapper getValue() {
		return getData();
	}
	
	
}
